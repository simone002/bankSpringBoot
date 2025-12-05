package com.simone.bankApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // 1. Disabilita CSRF (Fondamentale per i POST da Postman)
            .csrf(AbstractHttpConfigurer::disable)
            
            // 2. Permetti TUTTO (Non sicuro in produzione, ma perfetto per ora)
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll() 
            )
            
            // 3. Permetti i Frame per H2 Console
            .headers(headers -> headers.frameOptions(frame -> frame.disable()));

        return http.build();
    }
    
    // Lascia pure il PasswordEncoder qui sotto...
    @Bean
    public org.springframework.security.crypto.password.PasswordEncoder passwordEncoder() {
        return new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
    }
}