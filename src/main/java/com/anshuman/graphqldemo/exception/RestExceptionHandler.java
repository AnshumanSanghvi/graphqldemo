package com.anshuman.graphqldemo.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage globalExceptionHandler(Exception ex, WebRequest request) {
        logException(request, ex);
        return new ErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                new Date(),
                "Internal error encountered.",
                request.getDescription(false));
    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ErrorMessage accessDeniedException(AccessDeniedException ex, WebRequest request) {
        logException(request, ex);
        return new ErrorMessage(
                HttpStatus.FORBIDDEN.value(),
                new Date(),
                "Access denied for user.",
                request.getDescription(false));
    }

    @ExceptionHandler(value = {AuthenticationException.class})
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ErrorMessage authenticationException(AuthenticationException ex, WebRequest request) {
        logException(request, ex);
        return new ErrorMessage(
                HttpStatus.UNAUTHORIZED.value(),
                new Date(),
                "User not authenticated.",
                request.getDescription(false));
    }

    private static void logException(WebRequest request, Throwable throwable) {
        Throwable rootCause = ExceptionUtils.getRootCause(throwable);
        log.error("ControllerAdvice: Exception encountered at: {} for request: {}", rootCause, request);
    }

}
