package com.recruitment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDTO {
	
	@Schema(example = "user@gmail.com")
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
	private String email;
	
	@Schema(example = "password123")
    @NotBlank(message = "Password is required")
	private String password;
}
