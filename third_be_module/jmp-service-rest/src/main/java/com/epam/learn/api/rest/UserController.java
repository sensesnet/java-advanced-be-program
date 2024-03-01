package com.epam.learn.api.rest;

import com.epam.learn.dto.UserRequestDto;
import com.epam.learn.dto.UserResponseDto;
import com.epam.learn.service.api.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDto createUser(@Valid @RequestBody UserRequestDto request) {
        return userService.createUser(request);
    }

    @PutMapping
    public UserResponseDto updateUser(@Valid @RequestBody UserRequestDto request) {
        return userService.updateUser(request);
    }

    @GetMapping
    public List<UserResponseDto> getAllUsers() {
        return userService.getAllUser();
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/{id}")
    public UserResponseDto getUser(@PathVariable("id") Long id) {
        return userService.getUser(id);
    }
}
