package com.recruitment.dto;

import com.recruitment.model.User;
import com.recruitment.model.UserType;

import lombok.Data;

@Data
public class SignupRequestDTO {
	private String name;
	private String email;
	private String password;
	private String userType;
	private String address;
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
