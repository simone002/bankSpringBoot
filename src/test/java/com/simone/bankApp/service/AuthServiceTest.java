package com.simone.bankApp.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.simone.bankApp.dto.LoginResponse;
import com.simone.bankApp.dto.RegisterRequest;
import com.simone.bankApp.entity.User;

@SpringBootTest
@Transactional
public class AuthServiceTest {
    
    @Autowired
    private AuthService authService;
    
    @Test
    public void testLoginReturnsCompleteAccountInfo() {
        // Registra un nuovo utente
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("testuser");
        registerRequest.setPassword("password123");
        registerRequest.setEmail("test@example.com");
        registerRequest.setPin("1234");
        
        User registeredUser = authService.register(registerRequest);
        assertNotNull(registeredUser);
        assertNotNull(registeredUser.getId());
        
        // Login con l'utente registrato
        RegisterRequest loginRequest = new RegisterRequest();
        loginRequest.setUsername("testuser");
        loginRequest.setPassword("password123");
        
        LoginResponse loginResponse = authService.login(loginRequest);
        
        // Verifica che la risposta contenga tutte le informazioni
        assertNotNull(loginResponse);
        assertEquals(registeredUser.getId(), loginResponse.getUserId());
        assertEquals("testuser", loginResponse.getUsername());
        assertEquals("test@example.com", loginResponse.getEmail());
        assertEquals("ROLE_USER", loginResponse.getRole());
        assertNotNull(loginResponse.getIban());
        assertTrue(loginResponse.getIban().startsWith("IT"));
        assertNotNull(loginResponse.getBalance());
        assertEquals(BigDecimal.ZERO, loginResponse.getBalance());
    }
    
    @Test
    public void testLoginWithInvalidCredentials() {
        RegisterRequest loginRequest = new RegisterRequest();
        loginRequest.setUsername("nonexistent");
        loginRequest.setPassword("wrongpassword");
        
        Exception exception = assertThrows(RuntimeException.class, () -> {
            authService.login(loginRequest);
        });
        
        assertEquals("Credenziali non valide", exception.getMessage());
    }
}
