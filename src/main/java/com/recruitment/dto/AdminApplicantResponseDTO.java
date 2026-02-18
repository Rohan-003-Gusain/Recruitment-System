package com.recruitment.dto;

import java.util.List;

import lombok.Data;

@Data
public class AdminApplicantResponseDTO {

    private Long userId;
    private String name;
    private String email;
    private String profileHeadline;

    // profile data
    private List<String> skills;
    private List<String> education;
    private List<String> experience;
    
    private String resumeUrl;

    // applied jobs
    private List<AppliedJobDTO> appliedJobs;
}
