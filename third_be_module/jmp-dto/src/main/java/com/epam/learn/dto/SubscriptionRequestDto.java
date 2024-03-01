package com.epam.learn.dto;

import lombok.Builder;
import lombok.Value;
import jakarta.validation.constraints.NotNull;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder(toBuilder = true)
@Jacksonized
public class SubscriptionRequestDto {
    @NotNull
    Long id;
    @NotNull
    Long userId;
}
