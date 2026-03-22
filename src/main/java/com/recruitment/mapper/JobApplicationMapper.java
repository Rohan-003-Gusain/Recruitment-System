package com.recruitment.mapper;

import org.springframework.stereotype.Component;

import com.recruitment.dto.AdminJobApplicationDTO;
import com.recruitment.model.JobApplication;

@Component
public class JobApplicationMapper {

    public AdminJobApplicationDTO toDto(JobApplication app){

        AdminJobApplicationDTO dto = new AdminJobApplicationDTO();

        dto.setApplicationId(app.getId());

        dto.setApplicantId(app.getUser().getId());
        dto.setApplicantName(app.getUser().getName());
        dto.setApplicantEmail(app.getUser().getEmail());

        dto.setResumeUrl(
                app.getUser().getProfile() != null
                        ? app.getUser().getProfile().getResumeFilePath()
                        : null
        );

        dto.setAppliedOn(app.getAppliedOn());
        dto.setStatus(app.getStatus());

        return dto;
    }
}