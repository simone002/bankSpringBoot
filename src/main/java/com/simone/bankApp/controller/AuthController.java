package com.simone.bankApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simone.bankApp.dto.ErrorResponse;
import com.simone.bankApp.dto.LoginResponse;
import com.simone.bankApp.dto.RegisterRequest;
import com.simone.bankApp.service.AuthService;

@RestController
@RequestMapping("/auth") 
public class AuthController {

    @Autowired
    private AuthService authService;

    

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseEntity.ok("Utente registrato con successo!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody RegisterRequest request) {
        try {
            LoginResponse response = authService.login(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            ErrorResponse error = new ErrorResponse("INVALID_CREDENTIALS", "Credenziali non valide");
            return ResponseEntity.status(401).body(error);
        }
    }

    
}