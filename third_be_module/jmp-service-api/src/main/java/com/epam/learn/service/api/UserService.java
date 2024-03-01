package com.epam.learn.service.api;

import com.epam.learn.dto.UserRequestDto;
import com.epam.learn.dto.UserResponseDto;

import java.util.List;

public interface UserService {
    UserResponseDto createUser(UserRequestDto request);

    UserResponseDto updateUser(UserRequestDto request);

    void deleteUser(Long id);

    UserResponseDto getUser(Long id);

    List<UserResponseDto> getAllUser();
}
