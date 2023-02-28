FROM openjdk:17-jdk-slim

RUN mkdir /app
WORKDIR /app

COPY build/libs/*.jar app.jar

CMD ["java", "-jar", "app.jar"]