package com.example.task_25_04_2024.controller;

import com.example.task_25_04_2024.dto.AccountDto;
import com.example.task_25_04_2024.dto.UserDto;
import com.example.task_25_04_2024.dto.UserDtoWithDocNumAndDeposit;
import com.example.task_25_04_2024.entity.enums.DocType;
import com.example.task_25_04_2024.entity.enums.TransactionType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ExecutionException;

@SpringBootTest
class UserControllerIntegrationTest {

      private UserDtoWithDocNumAndDeposit userDtoWithDocNumAndDeposit;

      @Autowired
      private UserController userController;

      @BeforeEach
      void initializeUserDtoWithDocNumAndDeposit() {
            userDtoWithDocNumAndDeposit = new UserDtoWithDocNumAndDeposit(
                  "user1", DocType.PASSPORT, "1111111A001PB9",
                  1L, new BigDecimal("1000.00"), TransactionType.DEPOSIT
            );
      }

      @Test
      void testGetAllUsers() {
            ResponseEntity<List<UserDto>> allUsers = userController.getAllUsers();
            Assertions.assertEquals(5, allUsers.getBody().size());
      }

      @Test
      void testDepositUserAccount() throws ExecutionException, InterruptedException {
            UserDto userDto = userController
                  .depositUserAccount(userDtoWithDocNumAndDeposit)
                  .get()
                  .getBody();
            AccountDto accountDto = userDto.getAccounts()
                  .stream()
                  .findFirst()
                  .get();
            Assertions.assertEquals(new BigDecimal("1000.00"), accountDto.getBalance());
      }
}