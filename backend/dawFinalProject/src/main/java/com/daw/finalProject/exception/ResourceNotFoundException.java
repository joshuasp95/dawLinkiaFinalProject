package com.daw.finalProject.exception;

public class ResourceNotFoundException extends RuntimeException {
    public static final String ResourceNotFoundExceptionText = "ResourceNotFoundException";

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable exception) {
        super(message, exception);
    }

}
