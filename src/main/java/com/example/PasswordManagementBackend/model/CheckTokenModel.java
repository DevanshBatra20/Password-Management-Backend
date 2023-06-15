package com.example.PasswordManagementBackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckTokenModel {

    private String jwtToken;
    private String username;
}
