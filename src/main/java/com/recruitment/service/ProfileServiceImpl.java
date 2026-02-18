package com.recruitment.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.recruitment.exception.BadRequestException;
import com.recruitment.exception.ResourceNotFoundException;
import com.recruitment.model.Profile;
import com.recruitment.model.User;
import com.recruitment.repository.ProfileRepository;
import com.recruitment.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProfileServiceImpl implements ProfileService{

	private final UserRepository userRepository;
	private final ProfileRepository profileRepository;
	private final ResumeParserService resumeParserService;

	@Override
	public Profile saveResume(MultipartFile file) {
		
		String email = SecurityContextHolder
				.getContext()
				.getAuthentication()
				.getName();
		
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));
		
		if (file == null || file.isEmpty()) {
			throw new BadRequestException("Resume file is requried");
		}
		
		String contentType = file.getContentType();
		if (contentType == null ||
		    !(contentType.equals("application/pdf") ||
		      contentType.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document"))) {

		    throw new BadRequestException("Only PDF or DOCX files are allowed");
		}

		String uploadDir = "uploads/resume/";
		new File(uploadDir).mkdirs();
		
		String fileName = user.getId() + "_" + file.getOriginalFilename();
		Path filePath = Paths.get(uploadDir, fileName);
		
		try {
			Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new RuntimeException("Failed to store resume files", e);
		}
		
		Profile profile = profileRepository
		        .findByApplicant_Id(user.getId())
		        .orElse(new Profile());

		profile.setApplicant(user);
		profile.setResumeFilePath(filePath.toString());
		
		try {
			resumeParserService.fillProfileFromResume(file, profile);
		} catch (IOException e) {
			throw new RuntimeException("Resume parsing failed");
		}
		
		if (profile.getName() == null || profile.getName().isBlank()) {
	        profile.setName(user.getName());
	    }
		
		if (profile.getExperience() == null || profile.getExperience().isBlank()) {
	        profile.setExperience("Fresher");
	    }
		
		return profileRepository.save(profile);
	}

	@Override
	public Profile getProfileByUserId(long userId) {
		return profileRepository.findByApplicant_Id(userId)
				.orElseThrow(() -> new ResourceNotFoundException("Profile not found for userId: " + userId));
	}
	
}
