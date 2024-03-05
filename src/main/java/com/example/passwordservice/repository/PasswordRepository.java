package com.example.passwordservice.repository;

import com.example.passwordservice.model.Password;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface PasswordRepository extends MongoRepository<Password, String>{

    Page<Password> findByUserUserId(String userId, Pageable pageable);
    Page<Password> findByUserUserIdAndPasswordType(String userId, String passwordType, Pageable pageable);
}
