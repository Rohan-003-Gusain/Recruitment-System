package com.recruitment.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recruitment.dto.AdminApplicantResponseDTO;
import com.recruitment.dto.JobRequestDTO;
import com.recruitment.dto.JobResponseDTO;
import com.recruitment.dto.ProfileResponseDTO;
import com.recruitment.mapper.AdminApplicantMapper;
import com.recruitment.mapper.JobMapper;
import com.recruitment.mapper.ProfileMapper;
import com.recruitment.model.Job;
import com.recruitment.service.JobService;
import com.recruitment.service.ProfileService;
import com.recruitment.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
	
	private final JobService jobService;
	private final UserService userService;
	private final ProfileService profileService;
	private final JobMapper jobMapper;
	private final ProfileMapper profileMapper;
	private final AdminApplicantMapper adminApplicantMapper;

	// ========== CREATE JOB ==========
	@PostMapping("/job")
	public ResponseEntity<JobResponseDTO> createJob(@Valid @RequestBody JobRequestDTO dto) {
		Job job = jobService.createJob(dto);
		return ResponseEntity.ok(jobMapper.toJobResponseDTO(job));
	}
	
	// ========== GET ALL APPLICANTS ==========
	@GetMapping("/applicants")
	public ResponseEntity<List<AdminApplicantResponseDTO>> getAllApplicants() {
		
		List<AdminApplicantResponseDTO> response = userService.getAllApplicants()
				.stream()
				.map(adminApplicantMapper::toDto)
				.toList();
		
		return ResponseEntity.ok(response);
	}
	
	// ========== GET APPLICANT PROFILE ==========
	@GetMapping("/applicant/{applicantId}")
	public ResponseEntity<ProfileResponseDTO> getApplicantById(@PathVariable Long applicantId) {
		
		return ResponseEntity.ok(
				profileMapper.toProfileResponseDTO(profileService.getProfileByUserId(applicantId)));
	}

}
