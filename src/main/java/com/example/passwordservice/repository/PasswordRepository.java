package com.example.passwordservice.repository;

import com.example.passwordservice.model.Password;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PasswordRepository extends MongoRepository<Password, String>{
}
