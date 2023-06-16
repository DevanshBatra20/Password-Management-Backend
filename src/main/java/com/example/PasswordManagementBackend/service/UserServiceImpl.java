package com.example.PasswordManagementBackend.service;

import com.example.PasswordManagementBackend.entity.User;
import com.example.PasswordManagementBackend.entity.UserPassword;
import com.example.PasswordManagementBackend.exception.BaseException;
import com.example.PasswordManagementBackend.exception.InvalidCredentials;
import com.example.PasswordManagementBackend.exception.NoPasswordFoundException;
import com.example.PasswordManagementBackend.exception.UserNotFound;
import com.example.PasswordManagementBackend.model.ChangePasswordModel;
import com.example.PasswordManagementBackend.model.ImageModel;
import com.example.PasswordManagementBackend.model.UserModel;
import com.example.PasswordManagementBackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public String changePassword(Long userId, ChangePasswordModel changePasswordModel) throws BaseException {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null){
            throw new UserNotFound();
        }

        if (passwordEncoder.matches(changePasswordModel.getOldPassword(), user.getPassword())){
            user.setPassword(passwordEncoder.encode(changePasswordModel.getNewPassword()));
        } else {
            throw new InvalidCredentials();
        }
        userRepository.save(user);

        return "Password Changed successfully!!";
    }

    @Override
    public String deleteUser(Long userId) throws UserNotFound{
        User user = userRepository.findById(userId).orElse(null);
        if (user == null){
            throw new UserNotFound();
        } else {
            userRepository.delete(user);
        }
        return "Account Deleted successfully";
    }

    @Override
    public ResponseEntity<User> updateUser(Long userId, UserModel userModel) throws UserNotFound{
        User user = userRepository.findById(userId).orElse(null);
        if (user == null){
            throw new UserNotFound();
        }
        user.setFullName(userModel.getFullName());
        user.setEmail(userModel.getEmail());
        user.setUsername(userModel.getUsername());
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }

    @Override
    public String updateImage(Long userId, ImageModel imageModel) throws UserNotFound {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null){
            throw new UserNotFound();
        }
        user.setImageUrl(imageModel.getImageUrl());
        userRepository.save(user);

        return "Profile picture updated successfully!!";
    }

    @Override
    public List<UserPassword> getUserPasswords(Long userId) throws NoPasswordFoundException {
        List<UserPassword> passwords = userRepository.findById(userId).get().getUserPasswords();
        if (passwords == null){
            throw new NoPasswordFoundException();
        }
        return passwords;
    }

    @Override
    public User getUser(Long userId) throws UserNotFound {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null){
            throw new UserNotFound();
        }

        return user;
    }
}
