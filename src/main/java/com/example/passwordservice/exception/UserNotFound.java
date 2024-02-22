package com.example.passwordservice.exception;

import org.springframework.http.HttpStatus;

public class UserNotFound extends BaseException{

    public UserNotFound(String message) {super(message, HttpStatus.NOT_FOUND);}
}
