package com.example.task_25_04_2024.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
      @Bean
      public GroupedOpenApi userOpenApi() {
            String paths[] = {"/api/v1/**"};
            return GroupedOpenApi
                  .builder()
                  .group("API")
                  .pathsToMatch(paths)
                  .build();
      }

      @Bean
      public GroupedOpenApi actuatorOpenApi() {
            String paths[] = {"/actuator", "/actuator/info", "/actuator/health"};
            return GroupedOpenApi
                  .builder()
                  .group("Actuator")
                  .pathsToMatch(paths)
                  .build();
      }
}
