package com.example.task_25_04_2024.entity;

import com.example.task_25_04_2024.entity.enums.DocType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
@Table(name = "users")
@SequenceGenerator(sequenceName = "users_id_seq",
      name = "users_id_seq", allocationSize = 1)
@NamedEntityGraph(name = "UserWithNestedCollectionsGraph", attributeNodes = {@NamedAttributeNode(value = "accounts", subgraph = "UserWithNestedCollectionsGraph.Sub")},
      subgraphs = {
            @NamedSubgraph(name = "UserWithNestedCollectionsGraph.Sub", attributeNodes = @NamedAttributeNode(value = "transactions"))
      })
public class User {
      @Id
      @GeneratedValue(strategy = GenerationType.SEQUENCE, generator =
            "users_id_seq")
      @Column(name = "user_id")
      private Long id;

      @Column(name = "name", nullable = false)
      private String name;

      @Column(name = "document_type", nullable = false)
      @Enumerated(EnumType.STRING)
      @JdbcType(PostgreSQLEnumJdbcType.class)
      private DocType documentType;

      @Column(name = "document_number", unique = true, nullable = false)
      private String documentNumber;

      @Column(name = "created_at", nullable = false)
      @CreationTimestamp
      private Date createdAt;

      @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
      @JoinColumn(name = "user_id")
      @ToString.Exclude
      private Set<Account> accounts = new HashSet<>();

}
