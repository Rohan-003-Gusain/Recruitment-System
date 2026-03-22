package com.recruitment.service.impl;

import java.time.LocalDateTime;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.recruitment.dto.JobRequestDTO;
import com.recruitment.exception.ResourceNotFoundException;
import com.recruitment.exception.UnauthorizedException;
import com.recruitment.model.Job;
import com.recruitment.model.User;
import com.recruitment.model.UserType;
import com.recruitment.repository.JobRepository;
import com.recruitment.repository.UserRepository;
import com.recruitment.service.JobService;

@Service
public class JobServiceImpl implements JobService {

	private final JobRepository jobRepository;
	private final UserRepository userRepository;
	
	public JobServiceImpl(JobRepository jobRepository, UserRepository userRepository) {
		this.jobRepository = jobRepository;
		this.userRepository = userRepository;
	}
	
	// ========== ADMIN: CREATE JOB ==========
	@Override
	public Job createJob(JobRequestDTO dto) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (auth == null || !auth.isAuthenticated()) {
			throw new UnauthorizedException("User is not authenticated");
		}
		
		String email = auth.getName();
		
		User admin = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Admin not found"));
		
		if (admin.getUserType() != UserType.ADMIN) {
			throw new UnauthorizedException("Only admin can create jobs");
		}
		
		Job job = new Job();
		job.setTitle(dto.getTitle());
		job.setDescription(dto.getDescription());
		job.setCompanyName(dto.getCompanyName());
		job.setLocation(dto.getLocation());
		job.setSalary(dto.getSalary());
		
		job.setPostedBy(admin);
		job.setPostedOn(LocalDateTime.now());
		job.setTotalApplications(0);
		
		return jobRepository.save(job);
	}

	// ========== ADMIN / APPLICANT: GET JOB BY ID ==========
	@Override
	public Job getJobById(Long id) {
		return jobRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Job not found with id: " + id));
	}

	// ========== ALL USERS: GET ALL JOBS ==========
	@Override
	public List<Job> getAllJobs() {
		return jobRepository.findAll();
	}

}