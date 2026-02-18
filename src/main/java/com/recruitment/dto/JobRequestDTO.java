package com.recruitment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class JobRequestDTO {
	
	@NotBlank(message = "Job title is requried")
	private String title;
	
	@NotBlank(message = "Job description is requried")
	private String description;
	
	@NotBlank(message = "Company name is requried")
	private String companyName;
	
	@NotBlank(message = "Location is requried")
	private String location;
	
	@NotNull(message = "Salary must be positive")
	@Positive(message = "Salary must be greater than 0")
	private Integer salary;
}
