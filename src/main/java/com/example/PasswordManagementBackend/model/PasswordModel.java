package com.example.PasswordManagementBackend.model;

import com.example.PasswordManagementBackend.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordModel {

    private String password;
    private String imageUrl;
    private User user;
}
