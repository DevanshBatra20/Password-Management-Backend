package com.example.passwordservice.exception;

import org.springframework.http.HttpStatus;

public class UserAlreadyExist extends BaseException{

    public UserAlreadyExist(String message) {super(message, HttpStatus.BAD_REQUEST);}
}
