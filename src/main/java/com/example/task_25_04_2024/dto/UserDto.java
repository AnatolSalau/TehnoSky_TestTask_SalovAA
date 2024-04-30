package com.example.task_25_04_2024.dto;

import com.example.task_25_04_2024.entity.enums.DocType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
public class UserDto {

      private String name;

      private DocType documentType;

      private Date createdAt;

      private Set<AccountDto> accounts = new HashSet<>();
}
