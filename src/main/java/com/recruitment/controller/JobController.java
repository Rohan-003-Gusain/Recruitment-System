package com.recruitment.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.recruitment.dto.JobResponseDTO;
import com.recruitment.dto.ProfileResponseDTO;
import com.recruitment.mapper.JobMapper;
import com.recruitment.mapper.ProfileMapper;
import com.recruitment.service.JobApplicationService;
import com.recruitment.service.JobService;
import com.recruitment.service.ProfileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jobs")
public class JobController {
	
	private final JobService jobService;
    private final ProfileService profileService;
    private final JobApplicationService jobApplicationService;
    private final JobMapper jobMapper;
    private final ProfileMapper profileMapper;
	
	// ========== GET ALL JOBS ==========
	@GetMapping()
	public ResponseEntity<List<JobResponseDTO>> getAllJobs() {
		
		List<JobResponseDTO> response = jobService.getAllJobs()
				.stream()
				.map(jobMapper::toJobResponseDTO)
				.toList();
		
		return ResponseEntity.ok(response);
	}
	
	// ========== APPLY TO JOB ==========
	@PostMapping("/apply/{jobId}")
	public ResponseEntity<?> applyToJob(@PathVariable Long jobId) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		
		jobApplicationService.applyToJob(jobId, email);
		return ResponseEntity.ok("Job applied successfully");
	}
	
	// ========== UPLOAD RESUME ==========
	@PostMapping(
	        value = "/uploadResume",
	        consumes = MediaType.MULTIPART_FORM_DATA_VALUE
	)
	public ResponseEntity<ProfileResponseDTO> uploadResume(
	        @RequestParam("file") MultipartFile file) {

	    return ResponseEntity.ok(
	            profileMapper.toProfileResponseDTO(
	                    profileService.saveResume(file)
	            )
	    );
	}

}
