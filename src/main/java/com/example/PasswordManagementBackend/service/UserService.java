package com.example.PasswordManagementBackend.service;

import com.example.PasswordManagementBackend.entity.User;
import com.example.PasswordManagementBackend.entity.UserPassword;
import com.example.PasswordManagementBackend.model.ChangePasswordModel;
import com.example.PasswordManagementBackend.model.ImageModel;
import com.example.PasswordManagementBackend.model.UserModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    List<User> getUsers();

    String changePassword(Long userId, ChangePasswordModel changePasswordModel);

    String deleteUser(Long userId);

    ResponseEntity<User> updateUser(Long userId, UserModel userModel);

    String updateImage(Long userId, ImageModel imageModel);

    List<UserPassword> getUserPasswords(Long userId);

    User getUser(Long userId);
}
