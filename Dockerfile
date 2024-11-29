# Используем официальный образ Java
FROM openjdk:17-jdk-slim

# Устанавливаем рабочую директорию в контейнере
WORKDIR /app

# Копируем jar-файл приложения в контейнер
COPY ./target/simpleMoney-0.0.1-SNAPSHOT.jar app.jar

# Экспонируем порт
EXPOSE 8080

# Команда для запуска приложения
ENTRYPOINT ["java", "-jar", "app.jar"]