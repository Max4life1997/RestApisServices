package com.example.restapijpa.handler;

import com.example.restapijpa.exception.UserAlreadyExistException;
import com.example.restapijpa.exception.UserNotFoundException;
import com.example.restapijpa.model.ErrorJson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(String data) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ErrorJson("User not found error: ", data));
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<?> handleEntityAlreadyExistException(String data) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new ErrorJson("User already exist error: ", data));
    }
}
