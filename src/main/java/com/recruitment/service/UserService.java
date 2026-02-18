package com.recruitment.service;

import java.util.List;

import com.recruitment.model.User;
public interface UserService {
	User createUser(User user);
	String authenticate(String email, String password);
	List<User> getAllApplicants();
	User getUserById(long userId);
	
}