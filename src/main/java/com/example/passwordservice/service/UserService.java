package com.example.passwordservice.service;

import com.example.passwordservice.dto.response.UserDto;
import com.example.passwordservice.exception.InvalidParam;
import com.example.passwordservice.exception.UserNotFound;
import com.example.passwordservice.model.User;
import com.example.passwordservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDto getUserById(String userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new UserNotFound("User with userId " + userId + " not found!");
        }
        return mapUser(user);
    }

    public UserDto mapUser(User user) {

        return UserDto
                .builder()
                .userId(user.getUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .imageUrl(user.getImageUrl())
                .role(user.getRole())
                .build();
    }

    public void updateUserDetails(String userId, UserDto userDto) {
        if (userId == null) {
            throw new InvalidParam("UserId cannot be null");
        }
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new UserNotFound("User with userId " + userId + " not found!");
        }

        if (!userDto.getFirstName().isBlank() && !userDto.getFirstName().isEmpty()) user.setFirstName(userDto.getFirstName());
        if (!userDto.getLastName().isBlank() && !userDto.getLastName().isEmpty()) user.setLastName(userDto.getLastName());
        if (!userDto.getUsername().isBlank() && !userDto.getUsername().isEmpty()) user.setUsername(userDto.getUsername());
        if (!userDto.getEmail().isBlank() && !userDto.getEmail().isEmpty()) user.setEmail(userDto.getEmail());

        userRepository.save(user);
    }

    public void deleteUser(String userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new UserNotFound("User with userId " + userId + " not found!");
        }
        userRepository.delete(user);
    }
}
