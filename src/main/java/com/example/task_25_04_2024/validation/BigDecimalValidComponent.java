package com.example.task_25_04_2024.validation;

import java.math.BigDecimal;

public interface BigDecimalValidComponent {
      boolean checkScale(BigDecimal value, int scale);
}
