package com.bol.kalah.handler;

import com.bol.kalah.exception.ApplicationException;
import com.bol.kalah.exception.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    public final ResponseEntity<Error> handleApplicationException(final ApplicationException exception){
        Error error = Error
                .builder()
                .message(exception.getMessage())
                .status(HttpStatus.BAD_REQUEST)
                .build();
        return ResponseEntity.status(error.getStatus()).body(error);
    }
}
