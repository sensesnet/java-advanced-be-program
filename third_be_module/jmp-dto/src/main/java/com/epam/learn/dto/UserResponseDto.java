package com.epam.learn.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder(toBuilder = true)
@Jacksonized
public class UserResponseDto {
    @NotNull
    Long id;
    @NotEmpty
    String name;
    @NotEmpty
    String surname;
    @NotEmpty
    String birthday;
}
