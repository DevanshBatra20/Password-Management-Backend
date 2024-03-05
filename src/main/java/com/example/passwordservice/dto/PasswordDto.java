package com.example.passwordservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PasswordDto {

    private String passwordId;
    private String passwordName;
    private String password;
    private String passwordType;
    private String passwordImage;
}
