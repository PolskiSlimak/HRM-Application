package com.paw.hrmApp.exception;

import java.util.Collections;
import java.util.List;

public class ResourceNotFoundBody {
    private final Integer errorStatus;
    private final List<String> errors;

    public ResourceNotFoundBody (Integer errorStatus, List<String> errors) {
        this.errorStatus = errorStatus;
        this.errors = errors;
    }

    public ResourceNotFoundBody (Integer errorStatus, String errors) {
        this.errorStatus = errorStatus;
        this.errors = Collections.singletonList(errors);
    }
}
