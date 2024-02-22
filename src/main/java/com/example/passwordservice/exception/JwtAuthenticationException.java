package com.example.passwordservice.exception;

import org.springframework.http.HttpStatus;

public class JwtAuthenticationException extends BaseException{

    public JwtAuthenticationException(String message) { super(message, HttpStatus.UNAUTHORIZED);}
}
