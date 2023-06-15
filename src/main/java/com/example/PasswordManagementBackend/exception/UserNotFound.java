package com.example.PasswordManagementBackend.exception;

import org.springframework.http.HttpStatus;

public class UserNotFound extends BaseException{
    public UserNotFound() {
        super("User doesn't exist", HttpStatus.NOT_FOUND);
    }
}
