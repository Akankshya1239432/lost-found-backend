package com.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.model.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    // ✅ FIXED METHOD
    Optional<UserEntity> findFirstByEmail(String email);
}