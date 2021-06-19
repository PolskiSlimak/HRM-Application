package com.paw.hrmApp.controller;

import com.paw.hrmApp.exception.ResourceNotFoundBody;
import com.paw.hrmApp.exception.ResourceNotFoundException;
import com.paw.hrmApp.exception.UsernameNotFoundBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<ResourceNotFoundBody> handleDefaultException(ResourceNotFoundException e) {
        ResourceNotFoundBody body;
        HttpStatus status = HttpStatus.BAD_REQUEST;
        List<String> errors = e.getErrors();
        if (errors.isEmpty())
            body = new ResourceNotFoundBody(status.value(), e.getMessage());
        else
            body = new ResourceNotFoundBody(status.value(), e.getErrors());
        return new ResponseEntity<>(body, status);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    protected ResponseEntity<UsernameNotFoundBody> handleDefaultException(UsernameNotFoundException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        UsernameNotFoundBody body = new UsernameNotFoundBody(status.value(), e.getMessage());
        return new ResponseEntity<>(body, status);
    }
}
