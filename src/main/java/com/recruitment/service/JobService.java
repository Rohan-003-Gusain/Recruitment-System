package com.recruitment.service;

import java.util.List;

import com.recruitment.dto.JobRequestDTO;
import com.recruitment.model.Job;

public interface JobService {
	Job createJob(JobRequestDTO dto);
	Job getJobById(Long id);
	List<Job> getAllJobs();
}