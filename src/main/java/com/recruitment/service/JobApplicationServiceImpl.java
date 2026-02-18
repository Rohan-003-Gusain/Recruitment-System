package com.recruitment.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.recruitment.exception.BadRequestException;
import com.recruitment.exception.ResourceNotFoundException;
import com.recruitment.exception.UnauthorizedException;
import com.recruitment.model.Job;
import com.recruitment.model.JobApplication;
import com.recruitment.model.User;
import com.recruitment.model.UserType;
import com.recruitment.repository.JobApplicationRepository;
import com.recruitment.repository.JobRepository;
import com.recruitment.repository.UserRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class JobApplicationServiceImpl implements JobApplicationService{

	private final JobApplicationRepository jobApplicationRepository;
	private final UserRepository userRepository;
	private final JobRepository jobRepository;

	// ========== APPPLICANT: APPLY TO JOB ==========
	@Override
	public JobApplication applyToJob(Long jobId, String email) {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));
		
		if (user.getUserType() != UserType.APPLICANT) {
			throw new UnauthorizedException("Only applicants can apply for jobs");
		}
		
		Job job = jobRepository.findById(jobId)
				.orElseThrow(() -> new ResourceNotFoundException("job not found with id: " + jobId));
		
		if (jobApplicationRepository.existsByUser_EmailAndJob_Id(email, jobId)) {
			throw new BadRequestException("You have already applied for this job.");
		}
		
		JobApplication application = new JobApplication();
		application.setUser(user);
		application.setJob(job);
		application.setStatus("PENDING");
		application.setAppliedOn(LocalDateTime.now());
		
		jobApplicationRepository.save(application);
		
		job.setTotalApplications(job.getTotalApplications() + 1);
	    jobRepository.save(job);
		
		return application;
	}

	// ========== ADMIN: UPDATE APPLICATION STATUS ==========
	@Override
	public JobApplication updateStatus(Long applicationId, String status) {
		JobApplication application = jobApplicationRepository.findById(applicationId)
				.orElseThrow(() -> new ResourceNotFoundException("Application not found"));
		
		application.setStatus(status.toUpperCase());
		return jobApplicationRepository.save(application);
	}

	// ========== ADMIN: GET ALL APPLICTIONS BY JOB ID ==========
	@Override
	public List<JobApplication> getAllApplicationsById(Long jobId) {
		return jobApplicationRepository.findByJob_Id(jobId);
	}

	
}