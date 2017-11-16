package com.test.eventtracker.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ErrorController {
    private static final Logger log = LoggerFactory.getLogger(ErrorController.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleDictionaryException(MethodArgumentNotValidException ex) {
        log.error("Bad request", ex);
        return ResponseEntity.
                status(HttpStatus.BAD_REQUEST).
                contentType(MediaType.APPLICATION_JSON).
                body(new ErrorResponse(ex));
    }


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleDictionaryException(IllegalArgumentException ex) {

        return ResponseEntity.
                status(HttpStatus.BAD_REQUEST).
                contentType(MediaType.APPLICATION_JSON).
                body(new ErrorResponse(ex));
    }



    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorResponse> handleAllException(Throwable ex) {
        log.error("Unexpected service error", ex);
        return ResponseEntity.
                status(HttpStatus.INTERNAL_SERVER_ERROR).
                contentType(MediaType.APPLICATION_JSON).
                body(new ErrorResponse(ex));
    }

}
