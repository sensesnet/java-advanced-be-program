package com.epam.learn.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder(toBuilder = true)
@Jacksonized
public class SubscriptionResponseDto {
    @NotNull
    Long id;
    @NotNull
    Long userId;
    @NotEmpty
    String startDate;
}
