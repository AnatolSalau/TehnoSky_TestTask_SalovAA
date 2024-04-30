package com.example.task_25_04_2024.service;

import com.example.task_25_04_2024.dto.UserDto;
import com.example.task_25_04_2024.entity.enums.DocType;

import java.math.BigDecimal;
import java.util.List;

public interface UserService {
      List<UserDto> findAllUsers();

      UserDto saveUserWithDepositByDocNumber(String documentNumber, Long accountId, DocType docType, BigDecimal amount);
}
