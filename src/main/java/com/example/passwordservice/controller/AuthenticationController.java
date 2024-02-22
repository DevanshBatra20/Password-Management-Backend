package com.example.passwordservice.controller;

import com.example.passwordservice.dto.request.LoginRequestDto;
import com.example.passwordservice.dto.request.SignupRequestDto;
import com.example.passwordservice.dto.response.AuthenticationResponseDto;
import com.example.passwordservice.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<AuthenticationResponseDto> signup(
            @RequestBody SignupRequestDto signupRequestDto
            ) {
        return ResponseEntity.ok(authenticationService.signup(signupRequestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDto> login(
            @RequestBody LoginRequestDto loginRequestDto
            ) {
        return ResponseEntity.ok(authenticationService.login(loginRequestDto));
    }

}
