package com.recruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recruitment.model.Job;

public interface JobRepository extends JpaRepository<Job, Long>{

}
