package com.example.passwordservice.controller;

import com.example.passwordservice.dto.request.PasswordDto;
import com.example.passwordservice.service.PasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/password")
@RequiredArgsConstructor
public class PasswordController {

    private final PasswordService passwordService;

    @PostMapping("/createPassword")
    public String createPassword(
            @RequestParam(name = "userId") String userId,
            @RequestBody PasswordDto passwordDto) throws Exception
    {
        passwordService.createPassword(passwordDto, userId);
        return "Password Created successfully";
    }

    @GetMapping("/test")
    public String test() {
        return "Hello";
    }
}
