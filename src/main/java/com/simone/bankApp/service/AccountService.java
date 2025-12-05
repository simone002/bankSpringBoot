package com.simone.bankApp.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simone.bankApp.entity.Account;
import com.simone.bankApp.entity.Transaction;
import com.simone.bankApp.repository.AccountRepository;
import com.simone.bankApp.repository.TransactionRepository;

import jakarta.transaction.Transactional;

@Service
public class AccountService {
    
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public BigDecimal getAccountBalance(Long userId) {
        return accountRepository.findByUserId(userId)
                .map(Account::getBalance)
                .orElseThrow(() -> new RuntimeException("Account not found for user ID: " + userId));
    }

    @Transactional
    public Account deposit(Long userId, BigDecimal amount) { 

        // Usa 'amount' direttamente, senza getAmount()
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }

        Account account = accountRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Account not found for user ID: " + userId));

        // Usa 'amount'
        account.setBalance(account.getBalance().add(amount));
        Account savedAccount = accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setAccount(savedAccount);
        
        // Usa 'amount'
        transaction.setAmount(amount);
        transaction.setType("DEPOSIT");
        transaction.setDetails("Deposit to account ID: " + savedAccount.getId());
        transaction.setTimestamp(LocalDateTime.now());
        transaction.setBalanceAfter(savedAccount.getBalance());
        transaction.setCategory("deposit");

        transactionRepository.save(transaction);

        return savedAccount;
    }

    
    public List<Transaction> getTransactions(Long userId) {
        // 1. Troviamo il conto dell'utente
        Account account = accountRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        
        // 2. Usiamo l'ID del conto per trovare le transazioni
        return transactionRepository.findByAccountIdOrderByTimestampDesc(account.getId());
    }

    @Transactional
    public Account withdraw(Long userId, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }

        Account account = accountRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Account not found for user ID: " + userId));

        if (account.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient funds for withdrawal");
        }

        account.setBalance(account.getBalance().subtract(amount));
        Account savedAccount = accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setAccount(savedAccount);
        transaction.setAmount(amount.negate()); 
        transaction.setType("WITHDRAWAL");
        transaction.setDetails("Withdrawal from account ID: " + savedAccount.getId());
        transaction.setTimestamp(LocalDateTime.now());
        transaction.setBalanceAfter(savedAccount.getBalance());
        transaction.setCategory("withdrawal");

        transactionRepository.save(transaction);

        return savedAccount;
    }

}
