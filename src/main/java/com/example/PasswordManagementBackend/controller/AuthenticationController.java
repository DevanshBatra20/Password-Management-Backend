package com.example.PasswordManagementBackend.controller;

import com.example.PasswordManagementBackend.exception.EmailAlreadyInUse;
import com.example.PasswordManagementBackend.exception.InvalidCredentials;
import com.example.PasswordManagementBackend.exception.UserAlreadyExists;
import com.example.PasswordManagementBackend.model.AuthenticationRequestModel;
import com.example.PasswordManagementBackend.model.AuthenticationResponseModel;
import com.example.PasswordManagementBackend.model.CheckTokenModel;
import com.example.PasswordManagementBackend.model.RegisterRequestModel;
import com.example.PasswordManagementBackend.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponseModel> register(
            @RequestBody RegisterRequestModel registerRequestModel
            ) throws UserAlreadyExists, EmailAlreadyInUse {
        return ResponseEntity.ok(authenticationService.register(registerRequestModel));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponseModel> authenticate(
            @RequestBody AuthenticationRequestModel authenticationRequestModel
            ) throws InvalidCredentials {
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequestModel));
    }

    @GetMapping("/checkToken")
    public boolean checkToken(@RequestBody CheckTokenModel checkTokenModel){
        return authenticationService.checkToken(checkTokenModel);
    }
}
