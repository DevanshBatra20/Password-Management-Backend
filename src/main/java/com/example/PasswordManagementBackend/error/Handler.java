package com.example.PasswordManagementBackend.error;

import com.example.PasswordManagementBackend.exception.EmailAlreadyInUse;
import com.example.PasswordManagementBackend.exception.InvalidCredentials;
import com.example.PasswordManagementBackend.exception.UserAlreadyExists;
import com.example.PasswordManagementBackend.exception.UserNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestControllerAdvice
public class Handler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> userNotFoundExceptionHandler(UserNotFound userNotFound){
        HttpStatus httpStatus = userNotFound.getResponseHttpStatus();
        String message = userNotFound.getMessage();
        ErrorResponse errorResponse = new ErrorResponse( HttpStatus.NOT_FOUND.value(), userNotFound.getMessage());
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> userAlreadyExistExceptionHandler(UserAlreadyExists userAlreadyExists){
        HttpStatus httpStatus = userAlreadyExists.getResponseHttpStatus();
        String message = userAlreadyExists.getMessage();
        ErrorResponse errorResponse = new ErrorResponse( HttpStatus.BAD_REQUEST.value(), userAlreadyExists.getMessage());
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> emailAlreadyInUse(EmailAlreadyInUse emailAlreadyInUse){
        HttpStatus httpStatus = emailAlreadyInUse.getResponseHttpStatus();
        String message = emailAlreadyInUse.getMessage();
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), emailAlreadyInUse.getMessage());
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> invalidCredentials(InvalidCredentials invalidCredentials){
        HttpStatus httpStatus = invalidCredentials.getResponseHttpStatus();
        String message = invalidCredentials.getMessage();
        ErrorResponse errorResponse = new ErrorResponse(httpStatus.value(), invalidCredentials.getMessage());
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.UNAUTHORIZED);
    }
}
