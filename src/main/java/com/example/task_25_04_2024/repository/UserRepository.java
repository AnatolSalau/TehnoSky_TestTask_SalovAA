package com.example.task_25_04_2024.repository;


import com.example.task_25_04_2024.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
      @EntityGraph(value = "UserWithNestedCollectionsGraph")
      Optional<User> findUserByDocumentNumber(String DocumentNumber);

      @EntityGraph(value = "UserWithNestedCollectionsGraph")
      List<User> findAll();
}
