package com.example.passwordservice.service;

import com.example.passwordservice.dto.request.LoginRequestDto;
import com.example.passwordservice.dto.request.SignupRequestDto;
import com.example.passwordservice.dto.response.AuthenticationResponseDto;
import com.example.passwordservice.exception.InvalidCredentials;
import com.example.passwordservice.exception.UserAlreadyExist;
import com.example.passwordservice.model.Role;
import com.example.passwordservice.model.User;
import com.example.passwordservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponseDto signup(SignupRequestDto signupRequestDto) {

        if (userRepository.findByUsername(signupRequestDto.getUsername()).isPresent()){
            throw new UserAlreadyExist("User with username" + signupRequestDto.getUsername() + " already exist");
        }

        if (userRepository.findByEmail(signupRequestDto.getEmail()).isPresent()){
            throw new UserAlreadyExist("User with email" + signupRequestDto.getEmail() + " already exist");
        }

        var user = User.builder()
                .firstName(signupRequestDto.getFirstName())
                .lastName(signupRequestDto.getLastName())
                .email(signupRequestDto.getEmail())
                .username(signupRequestDto.getUsername())
                .password(passwordEncoder.encode(signupRequestDto.getPassword()))
                .role(Role.ROLE_USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponseDto.builder()
                .jwtToken(jwtToken)
                .user(user)
                .build();
    }

    public AuthenticationResponseDto login(LoginRequestDto loginRequestDto) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequestDto.getUsername(),
                            loginRequestDto.getPassword()
                    )
            );
        } catch (Exception exception) {
            throw new InvalidCredentials("Invalid username or password  " + exception);
        }

        var user = userRepository.findByUsername(loginRequestDto.getUsername())
                .orElse(null);

        if (user == null) {
            throw new InvalidCredentials("Invalid username or password");
        }

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponseDto.builder()
                .jwtToken(jwtToken)
                .user(user)
                .build();
    }
}
