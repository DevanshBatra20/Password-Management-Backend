package com.example.passwordservice.exception;

import org.springframework.http.HttpStatus;

public class InvalidCredentials extends BaseException{

    public InvalidCredentials(String message) { super(message, HttpStatus.UNAUTHORIZED);}
}
