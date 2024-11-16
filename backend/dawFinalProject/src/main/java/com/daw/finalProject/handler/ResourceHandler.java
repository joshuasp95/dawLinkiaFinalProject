package com.daw.finalProject.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.daw.finalProject.exception.ResourceNotFoundException;
import com.daw.finalProject.responseEntity.Response;
import com.daw.finalProject.responseEntity.ResponseEntityBuilder;

@ControllerAdvice
public class ResourceHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Response<Object>> handleResourceNotFound(ResourceNotFoundException exception) {
        return new ResponseEntityBuilder<>().notSuccessWithMessage(exception,
                ResourceNotFoundException.ResourceNotFoundExceptionText, HttpStatus.NOT_FOUND);
    }
}
