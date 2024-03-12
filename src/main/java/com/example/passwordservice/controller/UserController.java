package com.example.passwordservice.controller;

import com.example.passwordservice.dto.UserDto;
import com.example.passwordservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/getUser")
    public UserDto getUserById(@RequestParam(name = "userId") String userId) {
        return userService.getUserById(userId);
    }

    @PutMapping("/updateUser")
    public String updateUserDetails(@RequestParam(name = "userId") String userId, @RequestBody UserDto userDto) {
        userService.updateUserDetails(userId, userDto);
        return "User details updated successfully!";
    }

    @DeleteMapping("/deleteUser")
    public String deleteUser(@RequestParam(name = "userId") String userId) {
        userService.deleteUser(userId);
        return "User with userId " + userId + " deleted successfully!";
    }

    @PostMapping("/uploadProfilePhoto")
    public String uploadFile(
            @RequestParam(name = "userId") String userId,
            @RequestPart(value = "file") MultipartFile multipartFile) {
        userService.uploadImage(userId, multipartFile);

        return "Profile Picture updated successfully!";
    }
}
