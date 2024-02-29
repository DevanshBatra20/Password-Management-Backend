package com.example.passwordservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "passwords")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Password {

    @Id
    private String passwordId;
    private String passwordName;
    private String password;
    private String passwordType;
    private String passwordImage;

    @DBRef
    private User user;

}
