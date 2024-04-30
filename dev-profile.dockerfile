FROM openjdk:21

COPY build/libs .

EXPOSE 8081
EXPOSE 8087

ENTRYPOINT ["java", "-jar", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8087", "task_25_04_2024.jar"]