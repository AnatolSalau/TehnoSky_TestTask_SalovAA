package com.example.task_25_04_2024.repository;

import com.example.task_25_04_2024.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
