package com.example.PasswordManagementBackend.controller;

import com.example.PasswordManagementBackend.entity.User;
import com.example.PasswordManagementBackend.entity.UserPassword;
import com.example.PasswordManagementBackend.exception.InvalidCredentials;
import com.example.PasswordManagementBackend.exception.NoPasswordFoundException;
import com.example.PasswordManagementBackend.exception.UserNotFound;
import com.example.PasswordManagementBackend.model.ChangePasswordModel;
import com.example.PasswordManagementBackend.model.ImageModel;
import com.example.PasswordManagementBackend.model.UserModel;
import com.example.PasswordManagementBackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/getUser")
    public User getUserId(@RequestParam Long userId){
        return userService.getUser(userId);
    }

    @PutMapping("/changePassword")
    public String changePassword(
            @RequestParam Long userId,
            @RequestBody ChangePasswordModel changePasswordModel
            ) throws InvalidCredentials {
        return userService.changePassword(userId, changePasswordModel);
    }

    @DeleteMapping("/deleteUser")
    public String deleteUser(
            @RequestParam Long userId
    ) throws UserNotFound {
        return userService.deleteUser(userId);
    }

    @PutMapping("/updateUsers")
    public ResponseEntity<User> updateUser(
            @RequestParam Long userId,
            @RequestBody UserModel userModel
            ) throws UserNotFound {
        return userService.updateUser(userId, userModel);
    }

    @PutMapping("/updateImage")
    public String updateImage(
            @RequestParam Long userId,
            @RequestBody ImageModel imageModel){
        return userService.updateImage(userId, imageModel);
    }

    @GetMapping("/getPasswords")
    public List<UserPassword> getUserPasswords(@RequestParam Long userId) throws NoPasswordFoundException {
        return userService.getUserPasswords(userId);
    }

}
