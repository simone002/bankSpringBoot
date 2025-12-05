package com.simone.bankApp.entity;


import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "transactions")
@Data
public class Transaction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal amount; 

    @Column(nullable = false)
    private LocalDateTime timestamp=LocalDateTime.now();

    @Column(nullable = false, length = 10)
    private String type; 

    @Column(name="balance_after", nullable = false)
    private BigDecimal balanceAfter; 

    @Column(length = 50)
    private String category;  

    @Column(length = 120)
    private String details;  

    @ManyToOne
    @JoinColumn(name="account_id", nullable=false)
    private Account account;

}
