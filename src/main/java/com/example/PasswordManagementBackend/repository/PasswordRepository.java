package com.example.PasswordManagementBackend.repository;

import com.example.PasswordManagementBackend.entity.UserPassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordRepository extends JpaRepository<UserPassword, Long> {
}
