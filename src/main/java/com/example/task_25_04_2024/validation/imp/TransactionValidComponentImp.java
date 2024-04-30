package com.example.task_25_04_2024.validation.imp;

import com.example.task_25_04_2024.entity.enums.TransactionType;
import com.example.task_25_04_2024.validation.TransactionValidComponent;
import org.springframework.stereotype.Component;

@Component
public class TransactionValidComponentImp implements TransactionValidComponent {
      @Override
      public boolean compareTransactionType(TransactionType type1, TransactionType type2) {
            return type1 == type2;
      }
}
