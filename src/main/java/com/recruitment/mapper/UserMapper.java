package com.recruitment.mapper;

import org.springframework.stereotype.Component;

import com.recruitment.dto.UserResponseDTO;
import com.recruitment.model.User;

@Component
public class UserMapper {

    public UserResponseDTO toUserResponseDTO(User user) {
        if (user == null) return null;

        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setUserType(user.getUserType());
        return dto;
    }
}

