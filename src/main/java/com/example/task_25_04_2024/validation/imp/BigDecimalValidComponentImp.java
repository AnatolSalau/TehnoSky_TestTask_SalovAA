package com.example.task_25_04_2024.validation.imp;

import com.example.task_25_04_2024.validation.BigDecimalValidComponent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BigDecimalValidComponentImp implements BigDecimalValidComponent {
      @Override
      public boolean checkScale(BigDecimal value, int scale) {
            return value.scale() == scale;
      }
}
