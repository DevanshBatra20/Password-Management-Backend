package com.example.passwordservice.service;

import com.example.passwordservice.dto.PasswordDto;
import com.example.passwordservice.exception.InvalidParam;
import com.example.passwordservice.exception.UserNotFound;
import com.example.passwordservice.helper.AesEncryptionHelper;
import com.example.passwordservice.model.Password;
import com.example.passwordservice.model.User;
import com.example.passwordservice.repository.PasswordRepository;
import com.example.passwordservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<PasswordDto> getPasswordByUserId(String userId, String passwordType, int page, int size) {
        if (userId == null) {
            throw new InvalidParam("UserId param is required");
        }
        User user = userRepository.findById(userId).orElse(null);

        if (user == null) throw new UserNotFound("User with userId: " + userId + " not found");

        Page<Password> passwordsPage;
        Pageable pageable = PageRequest.of(page, size);

        if (passwordType.isEmpty() || passwordType.isBlank()) {
             passwordsPage = passwordRepository.findByUserUserId(userId, pageable);
        } else {
             passwordsPage = passwordRepository.findByUserUserIdAndPasswordType(userId, passwordType, pageable);
        }


        return passwordsPage.stream()
                .map(password -> {
                    try {
                        return PasswordDto.builder()
                                .passwordId(password.getPasswordId())
                                .passwordName(password.getPasswordName())
                                .password(aesEncryptionHelper.decryptAES(password.getPassword()))
                                .passwordType(password.getPasswordType())
                                .passwordImage(password.getPasswordImage())
                                .build();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
    }

    public void deletePassword(String passwordId) {
        Password password = passwordRepository.findById(passwordId).orElse(null);
        if (password == null) throw new UserNotFound("Password with password not found");
        passwordRepository.delete(password);
    }
}
