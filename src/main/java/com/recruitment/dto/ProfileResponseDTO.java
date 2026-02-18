package com.recruitment.dto;

import lombok.Data;

@Data
public class ProfileResponseDTO {
	private Long id;
    private String resumeFilePath;
    private String skills;
    private String education;
    private String experience;
    private String name;
    private String email;
    private String phone;
    
}
