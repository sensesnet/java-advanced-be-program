package com.epam.learn.api.rest;

import com.epam.learn.service.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@RestControllerAdvice
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handle(Exception exception,
                                HttpServletRequest request) {
        return error(request, ErrorCode.INTERNAL_SERVER_ERROR, null, exception);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handle(MethodArgumentNotValidException exception,
                                HttpServletRequest request) {
        return error(request, ErrorCode.BAD_REQUEST, null, exception);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handle(UserNotFoundException exception,
                                HttpServletRequest request) {
        return warn(request, ErrorCode.USER_NOT_FOUND, exception.getMessage(), exception);
    }

    @ExceptionHandler(SubscriptionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handle(SubscriptionNotFoundException exception,
                                HttpServletRequest request) {
        return warn(request, ErrorCode.SUBSCRIPTION_NOT_FOUND, exception.getMessage(), exception);
    }

    @ExceptionHandler(DateTimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handle(DateTimeException exception,
                                HttpServletRequest request) {
        return warn(request, ErrorCode.PARSE_DATE_ERROR, exception.getMessage(), exception);
    }

    private ErrorResponse warn(HttpServletRequest req,
                               ErrorCode errorCode,
                               String details,
                               Exception exception) {
        var headers = getHeaders(req);
        var response = buildErrorResponse(errorCode, details);
        var exceptionMessage = getExceptionMessage(exception);
        if (log.isDebugEnabled()) {
            log.warn(
                    "Request: {} {}?{}[{}] handled {}. Due to {}",
                    req.getMethod(),
                    req.getRequestURI(),
                    req.getQueryString(),
                    headers,
                    response,
                    exceptionMessage,
                    exception);
        } else {
            log.warn(
                    "Request: {} {}?{}[{}] handled {}. Due to {}",
                    req.getMethod(),
                    req.getRequestURI(),
                    req.getQueryString(),
                    headers,
                    response,
                    exceptionMessage);
        }
        return response;
    }

    private ErrorResponse error(HttpServletRequest req,
                                ErrorCode errorCode,
                                String details,
                                Exception exception) {
        var headers = getHeaders(req);
        var response = buildErrorResponse(errorCode, details);
        var exceptionMessage = getExceptionMessage(exception);
        log.error(
                "Unexpected failed request: {} {}?{}[{}] handled {}. Due to {}",
                req.getMethod(),
                req.getRequestURI(),
                req.getQueryString(),
                headers,
                response,
                exceptionMessage,
                exception);
        return response;
    }

    private String getHeaders(HttpServletRequest req) {
        Iterable<String> headersIter = () -> req.getHeaderNames().asIterator();
        return StreamSupport.stream(headersIter.spliterator(), false)
                .map(name -> "\"" + name + "\":\"" + req.getHeader(name) + "\"")
                .collect(Collectors.joining(","));
    }

    private String getExceptionMessage(Exception e) {
        String exceptionMsg = e.getClass().getSimpleName() + " - " + e.getMessage();
        if (e.getCause() != null && e.getCause().getMessage() != null) {
            exceptionMsg += " Cause: " + e.getCause().getMessage();
        }
        return exceptionMsg;
    }

    private ErrorResponse buildErrorResponse(ErrorCode errorCode,
                                             String details) {
        return ErrorResponse.builder()
                .incidentId(UUID.randomUUID())
                .errorCode(errorCode.name())
                .message(Objects.requireNonNullElse(details, errorCode.message))
                .build();
    }
}
