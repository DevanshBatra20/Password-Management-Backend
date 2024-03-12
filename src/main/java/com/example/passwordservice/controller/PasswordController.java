package com.example.passwordservice.controller;

import com.example.passwordservice.dto.PasswordDto;
import com.example.passwordservice.service.PasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/getPasswords")
    public ResponseEntity<List<PasswordDto>> getPasswordByUserId(
            @RequestParam(name = "userId") String userId,
            @RequestParam(name = "type", defaultValue = "all") String passwordType,
            @RequestParam(name = "pageNumber", defaultValue = "0") int page,
            @RequestParam(name = "pageSize", defaultValue = "10") int size) {
        return ResponseEntity.ok(passwordService.getPasswordByUserId(userId, passwordType,page, size));
    }

    @DeleteMapping("/deletePassword")
    public String deletePassword(@RequestParam(name = "passwordId") String passwordId) {
        passwordService.deletePassword(passwordId);
        return "Password removed successfully";
    }

    @PostMapping("/updatePassword")
    public String updatePassword(
            @RequestParam(name = "passwordId") String passwordId,
            @RequestBody PasswordDto passwordDto) {
        passwordService.updatePassword(passwordId, passwordDto);
        return "Password updated successfully";
    }

}
