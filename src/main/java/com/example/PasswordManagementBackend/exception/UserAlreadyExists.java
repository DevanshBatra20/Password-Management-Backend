package com.example.PasswordManagementBackend.exception;

import org.springframework.http.HttpStatus;

public class UserAlreadyExists extends BaseException{
    public UserAlreadyExists() {
        super("User already exist", HttpStatus.BAD_REQUEST);
    }
}
