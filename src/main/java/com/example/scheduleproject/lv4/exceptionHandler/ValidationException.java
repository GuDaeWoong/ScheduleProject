package com.example.scheduleproject.lv4.exceptionHandler;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;


public class ValidationException extends ResponseStatusException {
    private final String errors;
    public ValidationException(HttpStatusCode status, String errors) {
        super(status);
        this.errors = errors;
    }

    public String getErrors() {
        return errors;
    }
}
