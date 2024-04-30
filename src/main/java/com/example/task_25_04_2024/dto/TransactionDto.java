package com.example.task_25_04_2024.dto;

import com.example.task_25_04_2024.entity.enums.CurrencyType;
import com.example.task_25_04_2024.entity.enums.TransactionType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TransactionDto {
      private TransactionType transactionType;
      private BigDecimal amount;
      private CurrencyType currencyType;
      private Date createdAt;
}
