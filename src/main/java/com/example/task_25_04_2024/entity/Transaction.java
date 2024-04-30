package com.example.task_25_04_2024.entity;

import com.example.task_25_04_2024.entity.enums.CurrencyType;
import com.example.task_25_04_2024.entity.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
@Table(name = "transactions")
@SequenceGenerator(sequenceName = "transactions_id_seq",
      name = "transactions_id_seq", allocationSize = 1)
public class Transaction {
      @Id
      @GeneratedValue(strategy = GenerationType.SEQUENCE, generator =
            "transactions_id_seq")
      @Column(name = "transaction_id")
      private Long id;

      @Column(name = "account_id")
      private Long accountId;

      @Column(name = "transaction_type", nullable = false)
      @Enumerated(EnumType.STRING)
      @JdbcType(PostgreSQLEnumJdbcType.class)
      private TransactionType transactionType;

      @Column(name = "amount", precision = 10, scale = 2, nullable = false)
      private BigDecimal amount;

      @Column(name = "currency_type", nullable = false)
      @Enumerated(EnumType.STRING)
      @JdbcType(PostgreSQLEnumJdbcType.class)
      private CurrencyType currencyType;

      @Column(name = "created_at", nullable = false)
      @CreationTimestamp
      private Date createdAt;

      public Transaction(Long accountId, TransactionType transactionType, BigDecimal amount, CurrencyType currencyType) {
            this.accountId = accountId;
            this.transactionType = transactionType;
            this.amount = amount;
            this.currencyType = currencyType;
      }
}
