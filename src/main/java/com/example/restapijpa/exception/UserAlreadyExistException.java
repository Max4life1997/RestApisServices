package com.example.restapijpa.exception;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(String data) {
        super(String.format("User with data:'%s' already exist", data));
    }
}
