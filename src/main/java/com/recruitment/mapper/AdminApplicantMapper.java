package com.recruitment.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.recruitment.dto.AdminApplicantResponseDTO;
import com.recruitment.dto.AppliedJobDTO;
import com.recruitment.model.User;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AdminApplicantMapper {

    public AdminApplicantResponseDTO toDto(User user) {

        AdminApplicantResponseDTO dto = new AdminApplicantResponseDTO();

        dto.setUserId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setProfileHeadline(user.getProfileHeadline());

        if (user.getProfile() != null) {
            dto.setSkills(List.of(user.getProfile().getSkills().split(",")));
            dto.setEducation(List.of(user.getProfile().getEducation().split(",")));
            dto.setExperience(List.of(user.getProfile().getExperience().split(",")));
            dto.setResumeUrl(user.getProfile().getResumeFilePath());
        }

        dto.setAppliedJobs(
            user.getJobApplications()
                .stream()
                .map(app -> {
                    AppliedJobDTO jobDto = new AppliedJobDTO();
                    jobDto.setJobId(app.getJob().getId());
                    jobDto.setJobTitle(app.getJob().getTitle());
                    jobDto.setCompanyName(app.getJob().getCompanyName());
                    jobDto.setAppliedOn(app.getAppliedOn());
                    return jobDto;
                })
                .toList()
        );

        return dto;
    }
}
