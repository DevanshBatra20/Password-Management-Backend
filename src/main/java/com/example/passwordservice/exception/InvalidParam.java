package com.example.passwordservice.exception;

import org.springframework.http.HttpStatus;

public class InvalidParam extends BaseException{

    public InvalidParam(String message) { super(message, HttpStatus.BAD_REQUEST); }
}
