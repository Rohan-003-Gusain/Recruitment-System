package com.recruitment.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recruitment.model.User;
import com.recruitment.model.UserType;

public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByEmail(String email);

	List<User> findByUserType(UserType userType);
}
