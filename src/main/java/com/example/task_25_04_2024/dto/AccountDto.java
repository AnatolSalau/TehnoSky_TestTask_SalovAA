package com.example.task_25_04_2024.dto;

import com.example.task_25_04_2024.entity.enums.CurrencyType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
public class AccountDto {
      private Long id;
      private Long userId;
      private BigDecimal balance;
      private CurrencyType currencyType;
      private Date createdAt;
      private Set<TransactionDto> transactions = new HashSet<>();
}
