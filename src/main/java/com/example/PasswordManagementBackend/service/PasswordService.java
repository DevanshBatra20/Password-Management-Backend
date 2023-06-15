package com.example.PasswordManagementBackend.service;

import com.example.PasswordManagementBackend.entity.UserPassword;
import com.example.PasswordManagementBackend.model.ImageModel;
import com.example.PasswordManagementBackend.model.PasswordModel;
import com.example.PasswordManagementBackend.model.UpdatePasswordModel;
import org.springframework.http.ResponseEntity;

public interface PasswordService {
    ResponseEntity<UserPassword> createPassword(PasswordModel passwordModel);

    String updatePassword(Long passwordId, UpdatePasswordModel updatePasswordModel);

    String updateImage(Long passwordId, ImageModel imageModel);

    String deletePassword(Long passwordId);
}
