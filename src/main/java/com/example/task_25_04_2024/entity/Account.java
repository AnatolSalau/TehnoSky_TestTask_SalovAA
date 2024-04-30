package com.example.task_25_04_2024.entity;

import com.example.task_25_04_2024.entity.enums.CurrencyType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
@Table(name = "accounts")
@SequenceGenerator(sequenceName = "accounts_id_seq",
      name = "accounts_id_seq", allocationSize = 1)
public class Account {
      @Id
      @GeneratedValue(strategy = GenerationType.SEQUENCE, generator =
            "accounts_id_seq")
      @Column(name = "account_id")
      private Long id;

      @Column(name = "user_id")
      private Long userId;

      @Column(name = "balance", precision = 10, scale = 2, nullable = false)
      private BigDecimal balance;

      @Column(name = "currency_type", nullable = false)
      @Enumerated(EnumType.STRING)
      @JdbcType(PostgreSQLEnumJdbcType.class)
      private CurrencyType currencyType;

      @Column(name = "created_at", nullable = false)
      @CreationTimestamp
      private Date createdAt;

      @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
      @JoinColumn(name = "account_id")
      @ToString.Exclude
      private Set<Transaction> transactions = new HashSet<>();
}
