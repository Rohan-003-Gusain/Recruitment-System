package com.recruitment.mapper;

import org.springframework.stereotype.Component;

import com.recruitment.dto.JobRequestDTO;
import com.recruitment.dto.JobResponseDTO;
import com.recruitment.model.Job;

@Component
public class JobMapper {
	private final UserMapper userMapper;
	
	public JobMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	
	public JobResponseDTO toJobResponseDTO(Job job) {
		if (job == null) return null;
		
		JobResponseDTO dto = new JobResponseDTO();
		dto.setId(job.getId());
		dto.setTitle(job.getTitle());
		dto.setDescription(job.getDescription());
		dto.setCompanyName(job.getCompanyName());
		dto.setLocation(job.getLocation());
		dto.setSalary(job.getSalary());
		dto.setPostedOn(job.getPostedOn());
		dto.setTotalApplications(job.getTotalApplications());
		dto.setPostedBy(userMapper.toUserResponseDTO(job.getPostedBy()));
		return dto;
	}
	
	public Job toEntity(JobRequestDTO dto) {
		if (dto == null) return null;
		
		Job job = new Job();
		job.setTitle(dto.getTitle());
		job.setDescription(dto.getDescription());
		job.setCompanyName(dto.getCompanyName());
		job.setSalary(dto.getSalary());
		job.setLocation(dto.getLocation());
		
		return job;
		
	}
	
}
