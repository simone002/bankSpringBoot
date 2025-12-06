package com.simone.bankApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class AccountInfoResponse {
    private String username;
    private String iban;
    private BigDecimal balance;
}