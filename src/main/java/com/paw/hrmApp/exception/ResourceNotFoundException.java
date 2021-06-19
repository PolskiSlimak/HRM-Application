package com.paw.hrmApp.exception;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ResourceNotFoundException extends RuntimeException {
    private List<String> errors = new ArrayList<>();

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(List<String> errors) {
        this.errors = errors;
    }
}
