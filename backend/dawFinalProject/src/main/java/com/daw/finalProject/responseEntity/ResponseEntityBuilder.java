package com.daw.finalProject.responseEntity;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Data;
import lombok.extern.java.Log;

@Log
@Data
public class ResponseEntityBuilder<T> {

    private boolean success;
    private T data;
    private String message;
    private HttpStatus status;

    public ResponseEntity<Response<T>> notSuccessWithMessage(T errorCode, String message, HttpStatus status){
        Response<T> response = new Response<>(false, errorCode, message);
        printErrorMessage(message);
        return new ResponseEntity<>(response, status);
    }

    private void printErrorMessage(String errorMessage) {
        log.info("errorMessage: " + errorMessage);
    }
}
