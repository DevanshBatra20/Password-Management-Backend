package com.example.PasswordManagementBackend.service;

import com.example.PasswordManagementBackend.entity.UserPassword;
import com.example.PasswordManagementBackend.exception.NoPasswordFoundException;
import com.example.PasswordManagementBackend.model.ImageModel;
import com.example.PasswordManagementBackend.model.PasswordModel;
import com.example.PasswordManagementBackend.model.UpdatePasswordModel;
import com.example.PasswordManagementBackend.repository.PasswordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordServiceImpl implements PasswordService {

    private final PasswordRepository passwordRepository;
    @Override
    public ResponseEntity<UserPassword> createPassword(PasswordModel passwordModel) {
        UserPassword password = new UserPassword();
        password.setPassword(passwordModel.getPassword());
        password.setUsers(passwordModel.getUser());
        password.setImageUrl(password.getImageUrl());
        password.setName(passwordModel.getName());
        password.setType(passwordModel.getType());
        passwordRepository.save(password);
        return ResponseEntity.ok(password);
    }

    @Override
    public String updatePassword(Long passwordId, UpdatePasswordModel updatePasswordModel){
        UserPassword userPassword = passwordRepository.findById(passwordId).orElse(null);
        if (userPassword == null){
            throw new RuntimeException();
        }
        userPassword.setPassword(updatePasswordModel.getPassword());
        passwordRepository.save(userPassword);
        return "Password Updated Successfully";
    }

    @Override
    public String updateImage(Long passwordId, ImageModel imageModel) throws NoPasswordFoundException {
        UserPassword userPassword = passwordRepository.findById(passwordId).orElse(null);
        if (userPassword == null){
            throw new NoPasswordFoundException();
        }
        userPassword.setImageUrl(imageModel.getImageUrl());
        passwordRepository.save(userPassword);
        return "Image Updated Successfully";
    }

    @Override
    public String deletePassword(Long passwordId) {
        UserPassword userPassword = passwordRepository.findById(passwordId).orElse(null);
        passwordRepository.delete(userPassword);
        return "Password deleted!!";
    }
}
