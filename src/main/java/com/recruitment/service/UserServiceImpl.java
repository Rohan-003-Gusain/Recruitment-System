package com.recruitment.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.recruitment.config.JwtUtil;
import com.recruitment.exception.ConflictException;
import com.recruitment.exception.ResourceNotFoundException;
import com.recruitment.exception.UnauthorizedException;
import com.recruitment.model.User;
import com.recruitment.model.UserType;
import com.recruitment.repository.UserRepository;
@Service
public class UserServiceImpl implements UserService{

	private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }
	
	// ========== SIGN UP ==========
	@Override
	public User createUser(User user) {
		if (userRepository.findByEmail(user.getEmail()).isPresent()) {
		    throw new ConflictException("Email already registered");
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}
	
	// ========== LOGIN ==========
	@Override
	public String authenticate(String email, String password) {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UnauthorizedException("Invalid email or password"));
		
		if (!passwordEncoder.matches(password, user.getPassword())) {
			throw new UnauthorizedException("Invalid email or password");
		}
		return jwtUtil.generateToken(user);
	}
	
	// ========== ADMIN: GET ALL APPLICANTS ==========
	@Override
	public User getUserById(long userId) {
	    return userRepository.findById(userId)
	            .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
	}
	
	@Override
	public List<User> getAllApplicants() {
	    return userRepository.findByUserType(UserType.APPLICANT);
	}


}