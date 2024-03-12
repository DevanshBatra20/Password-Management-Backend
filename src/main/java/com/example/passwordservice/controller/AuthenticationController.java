package com.example.passwordservice.controller;

import com.example.passwordservice.dto.request.LoginRequestDto;
import com.example.passwordservice.dto.request.RefreshRequestDto;
import com.example.passwordservice.dto.request.SignupRequestDto;
import com.example.passwordservice.dto.response.AuthenticationResponseDto;
import com.example.passwordservice.dto.response.RefreshResponseDto;
import com.example.passwordservice.service.AuthenticationService;
import com.example.passwordservice.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

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

    @PostMapping("/refresh")
    public ResponseEntity<RefreshResponseDto> refresh(
            @RequestBody RefreshRequestDto refreshRequestDto
            ) {
        return ResponseEntity.ok(authenticationService.refresh(refreshRequestDto));
    }
}
