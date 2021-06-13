package com.nmhung.organization.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice(basePackages = "com.nmhung.organization")
public class RestExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RestErrorMessage> handleException(Exception ex) {
        var restMessage = new RestErrorMessage(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(restMessage, restMessage.getStatus());
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<RestErrorMessage> handleClientException(HttpClientErrorException ex) {
        var restMessage = new RestErrorMessage(ex.getStatusText(), ex.getStatusCode());
        return new ResponseEntity<>(restMessage, restMessage.getStatus());
    }

}
