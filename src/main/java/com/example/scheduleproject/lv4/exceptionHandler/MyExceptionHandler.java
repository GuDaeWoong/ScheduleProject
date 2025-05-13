package com.example.scheduleproject.lv4.exceptionHandler;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;


@RestControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> handleValidationException(ValidationException exception) {
        return new ResponseEntity<>(exception.getErrors(), HttpStatus.BAD_REQUEST);
    }
}
