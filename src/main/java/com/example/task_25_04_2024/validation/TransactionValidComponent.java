package com.example.task_25_04_2024.validation;

import com.example.task_25_04_2024.entity.enums.TransactionType;


public interface TransactionValidComponent {
      boolean compareTransactionType(TransactionType type1, TransactionType type2);
}
