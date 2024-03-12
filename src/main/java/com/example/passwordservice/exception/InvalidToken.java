package com.example.passwordservice.exception;

import org.springframework.http.HttpStatus;

public class InvalidToken extends BaseException{
    public InvalidToken(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}
