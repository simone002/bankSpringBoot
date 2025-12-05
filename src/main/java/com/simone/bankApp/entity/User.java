package com.simone.bankApp.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity; 
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data 
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;


    @Column(nullable = false)
    @JsonIgnore
    private String password;

    @Column(nullable = false,unique=true)
    private String email;

    @Column(nullable = false)
    private String role;

    @Column(columnDefinition="integer default 0")
    private Integer failedLoginAttempts=0;

    private LocalDateTime lockedUntil;

    public boolean isLocked(){
        return lockedUntil !=null && lockedUntil.isAfter(LocalDateTime.now());
    }
}
