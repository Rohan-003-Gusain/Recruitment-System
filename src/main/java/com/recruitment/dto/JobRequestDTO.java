package com.recruitment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class JobRequestDTO {
	
	@Schema(example = "Backend Developer")
	@NotBlank(message = "Job title is requried")
	private String title;
	
	@Schema(example = "Spring Boot + MySQL work")
	@NotBlank(message = "Job description is requried")
	private String description;
	
	@Schema(example = "Google")
	@NotBlank(message = "Company name is requried")
	private String companyName;
	
	@Schema(example = "Delhi")
	@NotBlank(message = "Location is requried")
	private String location;
	
	@Schema(example = "500000")
	@NotNull(message = "Salary must be positive")
	@Positive(message = "Salary must be greater than 0")
	private Integer salary;
}
