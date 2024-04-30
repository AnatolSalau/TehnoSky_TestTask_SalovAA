package com.example.task_25_04_2024.config;

import com.example.task_25_04_2024.exception_handler.ServiceAsyncExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {

      private final ServiceAsyncExceptionHandler asyncExceptionHandler;

      @Autowired
      public AsyncConfig(ServiceAsyncExceptionHandler asyncExceptionHandler) {
            this.asyncExceptionHandler = asyncExceptionHandler;
      }

      @Override
      public Executor getAsyncExecutor() {
            int systemPoolSize = Runtime.getRuntime().availableProcessors();
            ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
            executor.setMaxPoolSize(systemPoolSize);
            executor.setCorePoolSize(systemPoolSize);
            executor.setThreadNamePrefix("SERVICE ASYNC EXECUTOR-");
            executor.initialize();

            return executor;
      }

      @Override
      public ServiceAsyncExceptionHandler getAsyncUncaughtExceptionHandler() {
            return asyncExceptionHandler;
      }
}
