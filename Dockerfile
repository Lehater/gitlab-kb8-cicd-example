# Используем образ с JDK
FROM openjdk:17-jdk-slim

# Устанавливаем рабочую директорию
WORKDIR /app

# Устанавливаем необходимые зависимости, включая Maven
RUN apt-get update && apt-get install -y maven && rm -rf /var/lib/apt/lists/*

# Копируем POM и исходный код в контейнер
COPY pom.xml .
COPY src ./src

# Скачиваем зависимости
RUN mvn dependency:resolve

# Собираем приложение
RUN mvn package

# Указываем команду для запуска
CMD ["java", "-jar", "target/simple-java-app-1.0-SNAPSHOT.jar"]
