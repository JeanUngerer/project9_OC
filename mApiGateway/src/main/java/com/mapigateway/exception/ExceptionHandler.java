package com.mapigateway.exception;

import org.springframework.http.HttpStatus;

public class ExceptionHandler extends RuntimeException{

    public ExceptionHandler(String message) {
        super(message);
    }

    public ExceptionHandler(String message, Throwable cause) {
        super(message, cause);
    }

}
