package com.example.task_25_04_2024;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info =
@Info(title = "TestTask-API", version = "1.0", description = "Test task Tehnosky")
)
public class Task25042024Application {

      public static void main(String[] args) {
            SpringApplication.run(Task25042024Application.class, args);
      }

}
