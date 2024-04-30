package com.example.task_25_04_2024.controller;

import com.example.task_25_04_2024.dto.ErrorDto;
import com.example.task_25_04_2024.dto.UserDto;
import com.example.task_25_04_2024.dto.UserDtoWithDocNumAndDeposit;
import com.example.task_25_04_2024.entity.enums.TransactionType;
import com.example.task_25_04_2024.exception.UserRuntimeException;
import com.example.task_25_04_2024.service.UserService;
import com.example.task_25_04_2024.validation.BigDecimalValidComponent;
import com.example.task_25_04_2024.validation.TransactionValidComponent;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j

@RestController
@RequestMapping("/api/v1")
public class UserController {
      private final UserService userService;
      private final BigDecimalValidComponent bigDecimalValid;
      private final TransactionValidComponent transactionValid;

      @Autowired
      public UserController(UserService userService, BigDecimalValidComponent bigDecimalValid, TransactionValidComponent transactionValid) {
            this.userService = userService;
            this.bigDecimalValid = bigDecimalValid;
            this.transactionValid = transactionValid;
      }

      @GetMapping("/all")
      @ApiResponses({
            @ApiResponse(
                  responseCode = "200", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))
            ),
            @ApiResponse(
                  responseCode = "500", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            )
      })
      public ResponseEntity<List<UserDto>> getAllUsers() {
            List<UserDto> allUsers = userService.findAllUsers();
            if (allUsers.isEmpty()) {
                  throw new UserRuntimeException(500, "Users didn't found");
            }
            return ResponseEntity
                  .ok(allUsers);
      }

      @Async()
      @PostMapping("/deposit")
      @ApiResponses({
            @ApiResponse(
                  responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))
            ),
            @ApiResponse(
                  responseCode = "500", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            )
      })
      public CompletableFuture<ResponseEntity<UserDto>> depositUserAccount(@RequestBody UserDtoWithDocNumAndDeposit user) {

            if (!bigDecimalValid.checkScale(user.getAmount(), 2)) {
                  throw new UserRuntimeException(500, "Amount scale is wrong");
            }

            if (!transactionValid.compareTransactionType(TransactionType.DEPOSIT, user.getTransactionType())) {
                  throw new UserRuntimeException(500, "Transaction type is wrong");
            }

            UserDto userByDocumentNumber = userService.saveUserWithDepositByDocNumber(
                  user.getDocumentNumber(),
                  user.getAccountId(),
                  user.getDocumentType(),
                  user.getAmount()
            );
            return CompletableFuture.completedFuture(ResponseEntity.ok(userByDocumentNumber));

      }
}
