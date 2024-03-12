package com.example.passwordservice.service;

import com.example.passwordservice.exception.InvalidToken;
import com.example.passwordservice.exception.UserNotFound;
import com.example.passwordservice.model.RefreshToken;
import com.example.passwordservice.model.User;
import com.example.passwordservice.repository.RefreshTokenRepository;
import com.example.passwordservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    @Value("${refreshToken.expiry.days}")
    private String refreshTokenExpiryDays;

    public RefreshToken createRefreshToken(String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) {
            throw new UserNotFound("User with username " + username + " not found");
        }
        RefreshToken refreshToken = RefreshToken.builder()
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plus(Integer.parseInt(refreshTokenExpiryDays), ChronoUnit.DAYS))
                .user(user)
                .build();

        return refreshTokenRepository.save(refreshToken);
    }

    public RefreshToken findByToken(String token) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(token).orElse(null);
        if (refreshToken == null) {
            throw new InvalidToken("The provided token is invalid");
        }
        return refreshToken;
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
             token = createRefreshToken(token.getUser().getUsername());
        }
        return token;
    }
}
