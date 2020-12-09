package com.aarshinkov.masters.handlers;

import com.aarshinkov.masters.errors.CustomErrorResponse;
import com.aarshinkov.masters.errors.CustomException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionsHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<CustomErrorResponse> sfErrorHandler(CustomException ex) {
        CustomErrorResponse error = new CustomErrorResponse();
        error.setMessage(ex.getMessage());
        error.setCode(ex.getCode());
        error.setDetails(ex.getDetails());
        error.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(error, ex.getStatus());
    }
}
