package com.simone.bankApp.entity;


import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.ManyToAny;

/*

    id = db.Column(db.Integer, primary_key=True)
    amount = db.Column(db.Float, nullable=False) # positive for deposit, negative for withdrawal
    timestamp = db.Column(db.DateTime, nullable=False, default=datetime.now) # default to current time
    type = db.Column(db.String(10), nullable=False) # 'deposit' or 'withdrawal'
    user_id = db.Column(db.Integer, db.ForeignKey('user.id'), nullable=False) # foreign key to User
    user = db.relationship('User', backref=db.backref('transactions', lazy=True)) # relationship to User
    balance_after = db.Column(db.Float, nullable=False)  # balance after this transaction
    category = db.Column(db.String(50))  # es: "transfer_out" or "transfer_in"
    details = db.Column(db.String(120))  # es: "to mario@email.com"
 */

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
    private BigDecimal amount; // positive for deposit, negative for withdrawal

    @Column(nullable = false)
    private LocalDateTime timestamp=LocalDateTime.now(); // default to current time

    @Column(nullable = false, length = 10)
    private String type; // 'deposit' or 'withdrawal'

    @Column(name="balance_after", nullable = false)
    private BigDecimal balanceAfter;  // balance after this transaction

    @Column(length = 50)
    private String category;  // es: "transfer_out" or "transfer_in"

    @Column(length = 120)
    private String details;  // es: "to

    @ManyToOne
    @JoinColumn(name="account_id", nullable=false)
    private Account account;

}
