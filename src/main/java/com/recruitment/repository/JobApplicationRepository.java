package com.recruitment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recruitment.model.JobApplication;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long>{
	
	boolean existsByUser_EmailAndJob_Id(String email, Long jobId);
	
	List<JobApplication> findByJob_Id(Long jobId);
	
}
