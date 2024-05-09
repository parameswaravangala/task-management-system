package com.bank.taskmanagement.exception;

public class ResourceNotFoundException extends RuntimeException {


    public ResourceNotFoundException(String message, Throwable cause   ) {
        super(message, cause);
    }
    public ResourceNotFoundException(Throwable cause) {
        super(cause);
    }
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
