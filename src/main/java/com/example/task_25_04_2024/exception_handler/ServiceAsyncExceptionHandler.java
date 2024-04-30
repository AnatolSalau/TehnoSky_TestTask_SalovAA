package com.example.task_25_04_2024.exception_handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Slf4j

@Component
public class ServiceAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

      @Override
      public void handleUncaughtException(Throwable ex, Method method, Object... params) {
            log.error("[ASYNC-ERROR] method: {} exception: {}", method.getName(), ex);
      }
}
