package com.simone.bankApp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simone.bankApp.entity.User;

// Estendendo JpaRepository, ereditiamo gratis metodi come save(), findAll(), delete()
public interface UserRepository extends JpaRepository<User, Long> {

    // Spring crea automaticamente la query SQL per questo metodo basandosi sul nome!
    // "SELECT * FROM users WHERE username = ?"
    Optional<User> findByUsername(String username);
}