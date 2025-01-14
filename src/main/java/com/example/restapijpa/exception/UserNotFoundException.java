package com.example.restapijpa.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String data) {
        super(String.format("User with data:'%s' not found", data));
    }
}
