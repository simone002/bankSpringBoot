package com.simone.bankApp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simone.bankApp.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);
}