package com.example.passwordservice.service;

import com.example.passwordservice.dto.request.PasswordDto;
import com.example.passwordservice.exception.UserNotFound;
import com.example.passwordservice.helper.AesEncryptionHelper;
import com.example.passwordservice.model.Password;
import com.example.passwordservice.model.User;
import com.example.passwordservice.repository.PasswordRepository;
import com.example.passwordservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class PasswordService {

    private final AesEncryptionHelper aesEncryptionHelper;
    private final UserRepository userRepository;
    private final PasswordRepository passwordRepository;

    public void createPassword(PasswordDto passwordDto, String userId) throws Exception {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new UserNotFound("User with userId " + userId + " not found");
        }
        String encryptedPassword = aesEncryptionHelper.encryptAES(passwordDto.getPassword());
        Password password = Password
                .builder()
                .passwordName(passwordDto.getPasswordName())
                .passwordType(passwordDto.getPasswordType())
                .password(encryptedPassword)
                .user(user)
                .build();

        passwordRepository.save(password);
    }
}
