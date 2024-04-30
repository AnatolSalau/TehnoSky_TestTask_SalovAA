package com.example.task_25_04_2024.validation.imp;

import com.example.task_25_04_2024.entity.enums.TransactionType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransactionValidComponentImpTest {
      private TransactionValidComponentImp validator;

      @BeforeEach
      void initValidator() {
            validator = new TransactionValidComponentImp();
      }

      @Test
      void testCompareTransactionTypeWhenTypesEqual() {
            TransactionType type1 = TransactionType.DEPOSIT;
            TransactionType type2 = TransactionType.DEPOSIT;

            boolean isEqual = validator.compareTransactionType(type1, type2);

            Assertions.assertTrue(isEqual);
      }

      @Test
      void testCompareTransactionTypeWhenTypesDifferent() {
            TransactionType type1 = TransactionType.DEPOSIT;
            TransactionType type2 = TransactionType.WITHDRAWAL;

            boolean isEqual = validator.compareTransactionType(type1, type2);

            Assertions.assertFalse(isEqual);
      }
}