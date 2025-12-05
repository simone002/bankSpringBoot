package com.simone.bankApp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simone.bankApp.entity.Account;
public interface AccountRepository extends JpaRepository<Account, Long> {
    
    Optional<Account> findByUserId(Long userId);

    Optional<Account> findByIban(String iban);
}
