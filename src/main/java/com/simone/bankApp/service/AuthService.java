package com.simone.bankApp.service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.simone.bankApp.dto.RegisterRequest;
import com.simone.bankApp.entity.Account;
import com.simone.bankApp.entity.User;
import com.simone.bankApp.repository.AccountRepository;
import com.simone.bankApp.repository.UserRepository; 


@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AccountRepository accountRepository;

    public User register(RegisterRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword())); 
        user.setEmail(request.getEmail());

        user.setRole("ROLE_USER");

        User savedUser = userRepository.save(user);

        Account account = new Account();
        account.setUser(savedUser);
        account.setBalance(BigDecimal.ZERO);
        account.setIban("IT" + UUID.randomUUID().toString().substring(0, 10).toUpperCase());
        account.setPin(request.getPin());

        
        accountRepository.save(account);

        return savedUser;
        
    }

    public boolean login(RegisterRequest request) {
        Optional<User> userOpt = userRepository.findByUsername(request.getUsername());
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            return passwordEncoder.matches(request.getPassword(), user.getPassword());
        }
        return false;  
    }
}
