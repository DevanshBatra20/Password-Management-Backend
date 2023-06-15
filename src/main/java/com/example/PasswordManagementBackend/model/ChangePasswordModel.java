package com.example.PasswordManagementBackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordModel {

    private String oldPassword;
    private String newPassword;
}
