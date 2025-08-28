package com.doulsab.banking_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


public record AccountDto(Long id, String accountHolderName, double accountBalance, Long accountNumber) {
}