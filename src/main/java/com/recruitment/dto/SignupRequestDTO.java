package com.recruitment.dto;

import com.recruitment.model.User;
import com.recruitment.model.UserType;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignupRequestDTO {
	
	@Schema(example = "Rohan Gusain")
    @NotBlank(message = "Name is required")
	private String name;
	
	@Schema(example = "name@gmail.com")
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
	private String email;
	
	@Schema(example = "password123")
    @NotBlank(message = "Password is required")
	private String password;
	
	@Schema(example = "APPLICANT")
	private String userType;
	
	@Schema(example = "Delhi")
	private String address;
	
	@Schema(example = "Java Backend Developer")
	private String profileHeadline;
	
	public User toUser() {
		User user = new User();
		
		user.setName(this.name);
		user.setEmail(email);
		user.setPassword(password);
		user.setAddress(address);
		user.setProfileHeadline(profileHeadline);
		user.setUserType(UserType.valueOf(this.userType));
		
		user.setUserType(
	            userType != null ? UserType.valueOf(userType) : UserType.APPLICANT
	        );
		
		return user;
	}
	
}
