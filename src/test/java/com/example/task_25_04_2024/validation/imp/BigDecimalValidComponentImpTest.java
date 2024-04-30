package com.example.task_25_04_2024.validation.imp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class BigDecimalValidComponentImpTest {

      private BigDecimalValidComponentImp validator;

      @BeforeEach
      void initValidator() {
            validator = new BigDecimalValidComponentImp();
      }

      @Test
      void testCheckScaleWhenScalesEqual() {

            BigDecimal value = new BigDecimal("111.11");
            int scale = 2;

            Assertions.assertTrue(validator.checkScale(value, scale));
      }

      @Test
      void testCheckScaleWhenScalesDifferent() {
            BigDecimal value = new BigDecimal("111.11");
            int scale = 3;

            Assertions.assertFalse(validator.checkScale(value, scale));
      }
}