package com.simone.bankApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simone.bankApp.dto.RegisterRequest;
import com.simone.bankApp.service.AuthService;

@RestController
@RequestMapping("/auth") // Tutte le chiamate inizieranno con /auth
public class AuthController {

    @Autowired
    private AuthService authService;

    

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseEntity.ok("Utente registrato con successo!");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody RegisterRequest request) {
        boolean success = authService.login(request);
        if (success) {
            return ResponseEntity.ok("Login effettuato con successo!");
        } else {
            return ResponseEntity.status(401).body("Credenziali non valide");
        }
    }

    
}