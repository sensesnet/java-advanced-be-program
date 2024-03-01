package com.epam.learn.service.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ErrorCode {
    USER_NOT_FOUND("User not found"),
    SUBSCRIPTION_NOT_FOUND("Subscription not found"),
    PARSE_DATE_ERROR("Date parsing error"),
    BAD_REQUEST("Bad request"),
    INTERNAL_SERVER_ERROR("Internal Server Error");

    public final String message;
}
