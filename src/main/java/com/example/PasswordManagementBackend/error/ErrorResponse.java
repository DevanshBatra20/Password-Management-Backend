package com.example.PasswordManagementBackend.error;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ErrorResponse {

    private int Status;
    private String message;
}
