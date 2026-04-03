package com.model.controller;

import org.springframework.web.bind.annotation.*;
import com.model.entity.UserEntity;
import com.model.service.UserService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // =========================
    // REGISTER USER
    // =========================
    @PostMapping("/register")
    public UserEntity register(@RequestBody UserEntity user) {

        // basic validation
        if (user.getEmail() == null || user.getPassword() == null) {
            throw new RuntimeException("Email and Password required");
        }

        return userService.register(user);
    }

    // =========================
    // LOGIN USER
    // =========================
    @PostMapping("/login")
    public UserEntity login(@RequestBody UserEntity user) {

        if (user.getEmail() == null || user.getPassword() == null) {
            throw new RuntimeException("Invalid request");
        }

        return userService.login(user.getEmail(), user.getPassword());
    }

    // =========================
    // TEST API (OPTIONAL)
    // =========================
    @GetMapping("/test")
    public String test() {
        return "Auth API Working!";
    }
}