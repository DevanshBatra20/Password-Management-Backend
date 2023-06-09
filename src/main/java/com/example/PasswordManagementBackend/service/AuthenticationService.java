package com.example.PasswordManagementBackend.service;

import com.example.PasswordManagementBackend.entity.Role;
import com.example.PasswordManagementBackend.entity.User;
import com.example.PasswordManagementBackend.exception.EmailAlreadyInUse;
import com.example.PasswordManagementBackend.exception.InvalidCredentials;
import com.example.PasswordManagementBackend.exception.UserAlreadyExists;
import com.example.PasswordManagementBackend.exception.UserNotFound;
import com.example.PasswordManagementBackend.model.AuthenticationRequestModel;
import com.example.PasswordManagementBackend.model.AuthenticationResponseModel;
import com.example.PasswordManagementBackend.model.CheckTokenModel;
import com.example.PasswordManagementBackend.model.RegisterRequestModel;
import com.example.PasswordManagementBackend.repository.UserRepository;
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

    public AuthenticationResponseModel register(RegisterRequestModel registerRequestModel)  throws UserAlreadyExists, EmailAlreadyInUse {

        if (userRepository.findByUsername(registerRequestModel.getUsername()).isPresent()){
            throw new UserAlreadyExists();
        }

        if (userRepository.findByEmail(registerRequestModel.getEmail()).isPresent()){
            throw new EmailAlreadyInUse();
        }

            var user = User.builder()
                    .fullName(registerRequestModel.getFullName())
                    .username(registerRequestModel.getUsername())
                    .email(registerRequestModel.getEmail())
                    .password(passwordEncoder.encode(registerRequestModel.getPassword()))
                    .role(Role.ROLE_USER)
                    .build();
            userRepository.save(user);
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponseModel.builder()
                    .jwtToken(jwtToken)
                    .userId(user.getId())
                    .build();

    }

    public AuthenticationResponseModel authenticate(AuthenticationRequestModel authenticationRequestModel) throws InvalidCredentials {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequestModel.getUsername(),
                        authenticationRequestModel.getPassword()
                )
        );
        var user = userRepository.findByUsername(authenticationRequestModel.getUsername())
                .orElse(null);
        if (user == null){
            throw new InvalidCredentials();
        }
        var jwtToken = jwtService.generateToken(user);
        var id = user.getId();
        return AuthenticationResponseModel.builder()
                .jwtToken(jwtToken)
                .userId(id)
                .build();
    }

    public boolean checkToken(CheckTokenModel checkTokenModel) {
        String jwtToken = checkTokenModel.getJwtToken();
        return !jwtService.isTokenExpired(jwtToken);
    }
}
