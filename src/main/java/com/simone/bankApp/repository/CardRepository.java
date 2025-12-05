package com.simone.bankApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.simone.bankApp.entity.Card;
public interface CardRepository extends JpaRepository<Card, Long> {
    
    List<Card> findByAccountId(Long accountId);
}
