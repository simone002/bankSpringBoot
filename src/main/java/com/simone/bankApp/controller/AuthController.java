package com.simone.bankApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simone.bankApp.dto.RegisterRequest;
import com.simone.bankApp.entity.User;
import com.simone.bankApp.service.AuthService;

@RestController
@RequestMapping("/auth") 
public class AuthController {

    @Autowired
    private AuthService authService;

    

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            User newUser = authService.register(request);
            return ResponseEntity.ok(newUser); 
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); 
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody RegisterRequest request) {
        try {
            User user = authService.login(request);
            return ResponseEntity.ok(user); // Restituisce JSON { id: 1, username: "...", ... }
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    
}