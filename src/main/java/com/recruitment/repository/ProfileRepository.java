package com.recruitment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recruitment.model.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long>{
	
	Optional<Profile> findByApplicant_Id(Long userId);

}
