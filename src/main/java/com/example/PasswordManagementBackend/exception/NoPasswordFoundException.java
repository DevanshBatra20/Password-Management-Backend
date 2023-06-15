package com.example.PasswordManagementBackend.exception;

import org.springframework.http.HttpStatus;

public class NoPasswordFoundException extends BaseException{
    public NoPasswordFoundException() {
        super("No passwords found for the user", HttpStatus.NOT_FOUND);
    }
}
