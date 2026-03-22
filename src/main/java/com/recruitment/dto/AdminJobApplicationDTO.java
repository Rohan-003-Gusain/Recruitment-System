package com.recruitment.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AdminJobApplicationDTO {

	private Long applicationId;
    private Long applicantId;
    private String applicantName;
    private String applicantEmail;
    private String resumeUrl;
    private LocalDateTime appliedOn;
    private String status;

}