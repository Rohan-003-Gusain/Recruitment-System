package com.recruitment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recruitment.dto.LoginRequestDTO;
import com.recruitment.dto.SignupRequestDTO;
import com.recruitment.service.UserService;

@RestController
@RequestMapping()
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	// ========== SIGNUP ==========
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody SignupRequestDTO dto) {
		return ResponseEntity.ok(userService.createUser(dto.toUser()));
	}
	
	// ========== LOGIN ==========
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequestDTO dto) {
		String token = userService.authenticate(dto.getEmail(), dto.getPassword());
		return ResponseEntity.ok(token);
	}
	
}
