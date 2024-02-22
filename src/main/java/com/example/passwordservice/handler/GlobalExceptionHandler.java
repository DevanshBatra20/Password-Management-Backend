package com.example.passwordservice.handler;

import com.example.passwordservice.dto.response.ErrorResponse;
import com.example.passwordservice.exception.BaseException;
import com.example.passwordservice.exception.JwtAuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(BaseException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getHttpStatus().value(), exception.getMessage());
        return ResponseEntity.status(exception.getHttpStatus()).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentsNotValid(MethodArgumentNotValidException exception) {
        return ResponseEntity.badRequest().body(Objects.requireNonNull(exception.getBindingResult().getFieldError()).getDefaultMessage());
    }
}
