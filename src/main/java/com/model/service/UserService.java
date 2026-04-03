package com.model.service;

import org.springframework.stereotype.Service;
import com.model.entity.UserEntity;
import com.model.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 🔥 REGISTER (SET DEFAULT ROLE = USER)
    public UserEntity register(UserEntity user) {

        if (user.getRole() == null || user.getRole().isBlank()) {
            user.setRole("USER");
        }

        return userRepository.save(user);
    }

    // 🔥 LOGIN
    public UserEntity login(String email, String password) {

        Optional<UserEntity> userOptional =
                userRepository.findFirstByEmail(email);

        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        UserEntity user = userOptional.get();

        if (user.getPassword() == null || !user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }

        return user;   // includes role (ADMIN / USER)
    }
}