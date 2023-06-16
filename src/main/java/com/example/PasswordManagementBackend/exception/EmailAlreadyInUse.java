package com.example.PasswordManagementBackend.exception;

import org.springframework.http.HttpStatus;

public class EmailAlreadyInUse extends BaseException {

    public EmailAlreadyInUse(){
        super("Email already in use", HttpStatus.BAD_REQUEST);
    }
}
