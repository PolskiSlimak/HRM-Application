package com.paw.hrmApp.exception;

import lombok.Getter;

public class UsernameNotFoundBody {
    private final Integer errorStatus;
    private final String errorMessage;

    public UsernameNotFoundBody(Integer errorStatus, String errorMessage) {
        this.errorStatus = errorStatus;
        this.errorMessage = errorMessage;
    }
}
