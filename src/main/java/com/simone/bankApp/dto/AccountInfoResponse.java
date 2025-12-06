package com.simone.bankApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountInfoResponse {
    private Long userId;
    private String username;
    private String email;
    private Long accountId;
    private String iban;
    private BigDecimal balance;
}
