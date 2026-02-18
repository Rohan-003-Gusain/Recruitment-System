package com.recruitment.dto;

import com.recruitment.model.UserType;

import lombok.Data;

@Data
public class UserResponseDTO {
	private Long id;
	private String name;
	private String email;
	private UserType userType;
	
}
