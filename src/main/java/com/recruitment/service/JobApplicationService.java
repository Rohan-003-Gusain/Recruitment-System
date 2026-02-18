package com.recruitment.service;

import java.util.List;

import com.recruitment.model.JobApplication;
public interface JobApplicationService {
	JobApplication applyToJob(Long jobId, String email);
	JobApplication updateStatus(Long id, String status);
	List<JobApplication> getAllApplicationsById(Long jobId);
}