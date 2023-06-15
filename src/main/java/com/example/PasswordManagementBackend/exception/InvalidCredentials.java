package com.example.PasswordManagementBackend.exception;

import org.springframework.http.HttpStatus;

public class InvalidCredentials extends BaseException{
    public InvalidCredentials() {
        super("Invalid Credentials", HttpStatus.UNAUTHORIZED);
    }
}
