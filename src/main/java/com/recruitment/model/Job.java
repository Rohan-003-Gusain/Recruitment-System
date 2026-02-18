package com.recruitment.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Job {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String description;
	private String companyName;
	
	private String location;
	private Integer salary;
	
	private LocalDateTime postedOn = LocalDateTime.now();;
	private Integer totalApplications = 0;
	
	@ManyToOne
	@JoinColumn(name = "posted_by")
	private User postedBy;

}
