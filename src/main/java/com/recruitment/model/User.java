package com.recruitment.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private String password;
	private String address;
	private String profileHeadline;
	
	@Enumerated(EnumType.STRING)
	private UserType userType;
	
	@OneToOne(mappedBy = "applicant", cascade = CascadeType.ALL)
	private Profile profile;
	
	@OneToMany(mappedBy = "user")
	private List<JobApplication> jobApplications;
	
}
