package com.example.PasswordManagementBackend.controller;

import com.example.PasswordManagementBackend.entity.User;
import com.example.PasswordManagementBackend.entity.UserPassword;
import com.example.PasswordManagementBackend.model.ImageModel;
import com.example.PasswordManagementBackend.model.PasswordModel;
import com.example.PasswordManagementBackend.model.UpdatePasswordModel;
import com.example.PasswordManagementBackend.service.PasswordService;
import com.example.PasswordManagementBackend.service.UserService;
import lombok.Getter;
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
    public ResponseEntity<UserPassword> createPassword(@RequestBody PasswordModel passwordModel){
        return passwordService.createPassword(passwordModel);
    }

    @PutMapping("/updatePassword")
    public String updatePassword(
            @RequestParam Long passwordId,
            @RequestBody UpdatePasswordModel updatePasswordModel){
       return passwordService.updatePassword(passwordId, updatePasswordModel);
    }

    @PutMapping("/updateImage")
    public String updateImage(
            @RequestParam Long passwordId,
            @RequestBody ImageModel imageModel
            ){
        return passwordService.updateImage(passwordId, imageModel);
    }

    @DeleteMapping("/deletePassword")
    public String deletePassword(@RequestParam Long passwordId){
        return passwordService.deletePassword(passwordId);
    }
}
