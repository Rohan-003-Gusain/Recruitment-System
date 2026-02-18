package com.recruitment.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class JobResponseDTO {
	private Long id;
	private String title;
	private String description;
	private String companyName;
	private String location;
	private Integer salary;
	private LocalDateTime postedOn;
	private int totalApplications;
	private UserResponseDTO postedBy;
	
}
