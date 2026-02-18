package com.recruitment.mapper;

import org.springframework.stereotype.Component;

import com.recruitment.dto.ProfileResponseDTO;
import com.recruitment.model.Profile;

@Component
public class ProfileMapper {

    public ProfileResponseDTO toProfileResponseDTO(Profile profile) {
        if (profile == null) return null;

        ProfileResponseDTO dto = new ProfileResponseDTO();
        dto.setId(profile.getId());
        dto.setName(profile.getName());
        dto.setEmail(profile.getEmail());
        dto.setPhone(profile.getPhone());
        dto.setSkills(profile.getSkills());
        dto.setEducation(profile.getEducation());
        dto.setExperience(profile.getExperience());
        dto.setResumeFilePath(profile.getResumeFilePath());
        return dto;
    }
}
