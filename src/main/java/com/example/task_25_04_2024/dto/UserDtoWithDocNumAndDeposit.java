package com.example.task_25_04_2024.dto;

import com.example.task_25_04_2024.entity.enums.DocType;
import com.example.task_25_04_2024.entity.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class UserDtoWithDocNumAndDeposit {

      private String name;

      private DocType documentType;

      private String documentNumber;

      private Long accountId;

      private BigDecimal amount;

      private TransactionType transactionType;

}
