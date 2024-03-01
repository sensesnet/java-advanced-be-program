package com.epam.learn.service.impl;

import com.epam.learn.dto.UserRequestDto;
import com.epam.learn.dto.UserResponseDto;
import com.epam.learn.model.User;
import com.epam.learn.service.api.UserService;
import com.epam.learn.service.exception.UserNotFoundException;
import com.epam.learn.service.repository.UserRepository;
import com.epam.learn.service.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserResponseDto createUser(@Valid UserRequestDto request) {
        User user = User.builder()
                .name(request.getName())
                .surName(request.getSurname())
                .birthday(Utils.parseDate(request.getBirthday()))
                .build();
        user = userRepository.save(user);
        return toResponse(user);
    }

    @Override
    public UserResponseDto updateUser(@Valid UserRequestDto request) {
        Optional<User> searchedUser = userRepository.findById(request.getId());
        if (searchedUser.isPresent()) {
            User user = searchedUser.get();
            user.setName(request.getName());
            user.setSurName(request.getSurname());
            user.setBirthday(Utils.parseDate(request.getBirthday()));
            User updatedUser = userRepository.save(user);
            return toResponse(updatedUser);
        } else {
            throw new UserNotFoundException("User not found with ID: " + request.getId());
        }
    }

    @Override
    public void deleteUser(@Valid Long id) {
        Optional<User> user = userRepository.findById(id);
        user.ifPresent(value -> userRepository.delete(value));
    }

    @Override
    public UserResponseDto getUser(@Valid Long id) {
        return userRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new UserNotFoundException(String.format(
                        "The user is not found by '%s' id.",
                        id)));
    }

    @Override
    public List<UserResponseDto> getAllUser() {
        return userRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private UserResponseDto toResponse(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurName())
                .birthday(user.getBirthday().toString())
                .build();
    }
}
