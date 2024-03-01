package com.epam.learn.service.exception;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Value
@Builder(toBuilder = true)
@Jacksonized
public class ErrorResponse {
    UUID incidentId;
    String errorCode;
    String message;
}
