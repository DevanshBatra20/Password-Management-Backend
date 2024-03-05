package com.example.passwordservice.service;

import com.example.passwordservice.dto.response.UserDto;
import com.example.passwordservice.exception.InvalidParam;
import com.example.passwordservice.exception.UserNotFound;
import com.example.passwordservice.model.User;
import com.example.passwordservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final AmazonClient amazonClient;

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

    public void uploadImage(String userId, MultipartFile multipartFile) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new UserNotFound("User with userId " + userId + " not found");
        }
        if (user.getImageUrl() != null) {
           try {
              amazonClient.deleteFileFromS3Bucket(user.getImageUrl());
           } catch (Exception e) {
               log.error(e.getLocalizedMessage());
           }
      }
        String imageUrl = amazonClient.uploadFile(multipartFile);
        user.setImageUrl(imageUrl);
        userRepository.save(user);
    }
}
