package com.example.PasswordManagementBackend.controller;

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
            ){
        return ResponseEntity.ok(authenticationService.register(registerRequestModel));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponseModel> authenticate(
            @RequestBody AuthenticationRequestModel authenticationRequestModel
            ){
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequestModel));
    }

    @GetMapping("/checkToken")
    public boolean checkToken(@RequestBody CheckTokenModel checkTokenModel){
        return authenticationService.checkToken(checkTokenModel);
    }
}
