package com.simone.bankApp.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simone.bankApp.dto.AmountRequest;
import com.simone.bankApp.entity.Account;
import com.simone.bankApp.entity.Transaction;
import com.simone.bankApp.repository.AccountRepository;
import com.simone.bankApp.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {


    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;


    @GetMapping("/{userId}/balance") // Cambia {accountId} in {userId}
    public ResponseEntity<BigDecimal> getBalance(@PathVariable Long userId) {
        
        // Usa il service che cerca per UserID, invece del repository diretto
        BigDecimal balance = accountService.getAccountBalance(userId);
        return ResponseEntity.ok(balance);
    }


    @PostMapping("/{userId}/deposit")
    public ResponseEntity<Account> deposit(
            @PathVariable Long userId, 
            @RequestBody AmountRequest request) {
        
        Account updatedAccount = accountService.deposit(userId, request.getAmount());
        return ResponseEntity.ok(updatedAccount);
    }

    @GetMapping("/{userId}/transactions")
    public ResponseEntity<List<Transaction>> getTransactions(@PathVariable Long userId) {
        return ResponseEntity.ok(accountService.getTransactions(userId));
    }

    @PostMapping("/{userId}/withdraw")
    public ResponseEntity<Account> withdraw(@PathVariable Long userId, @RequestBody AmountRequest request) {
        
        Account updatedAccount = accountService.withdraw(userId, request.getAmount());
        return ResponseEntity.ok(updatedAccount);
    }


}
