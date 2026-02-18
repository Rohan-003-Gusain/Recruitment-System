package com.recruitment.service;

import org.springframework.web.multipart.MultipartFile;

import com.recruitment.model.Profile;

public interface ProfileService {
	Profile saveResume(MultipartFile file);
	Profile getProfileByUserId(long userId);
}
