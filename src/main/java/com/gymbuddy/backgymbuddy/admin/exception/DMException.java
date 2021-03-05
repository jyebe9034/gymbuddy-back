package com.gymbuddy.backgymbuddy.admin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DMException extends RuntimeException {

    public DMException() {}

    public DMException(String message) {
        super(message);
    }
}
