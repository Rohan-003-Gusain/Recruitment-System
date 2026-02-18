package com.recruitment.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AppliedJobDTO {
    private Long jobId;
    private String jobTitle;
    private String companyName;
    private LocalDateTime appliedOn;
}
