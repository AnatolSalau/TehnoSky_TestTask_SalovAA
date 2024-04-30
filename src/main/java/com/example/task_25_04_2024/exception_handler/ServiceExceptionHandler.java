package com.example.task_25_04_2024.exception_handler;

import com.example.task_25_04_2024.dto.ErrorDto;
import com.example.task_25_04_2024.exception.UserRuntimeException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ServiceExceptionHandler {
      @ExceptionHandler(value = {UserRuntimeException.class})
      public ResponseEntity<ErrorDto> handleUserRuntimeException(UserRuntimeException userRuntimeException) {
            return ResponseEntity
                  .status(userRuntimeException.getStatusCode())
                  .body(new ErrorDto(
                              userRuntimeException.getStatusCode(),
                              userRuntimeException.getMessage()
                        )
                  );
      }
}
