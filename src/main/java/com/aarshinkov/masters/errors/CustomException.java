package com.aarshinkov.masters.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomException extends RuntimeException {
    private Integer code;
    private String message;
    private String details;
    private HttpStatus status;
}
