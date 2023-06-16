package com.example.PasswordManagementBackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "passwordTable")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPassword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String password;
    private String imageUrl;
    private String name;
    private String type;

    @ManyToOne
    @JoinColumn(name = "user_list" )
    private User users;

}
