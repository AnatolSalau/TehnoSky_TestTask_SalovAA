package com.example.task_25_04_2024.exception;

import lombok.Getter;

@Getter
public class UserRuntimeException extends RuntimeException {
      private int statusCode;

      public UserRuntimeException(int statusCode, String message) {
            super(message);
            this.statusCode = statusCode;
      }
}
