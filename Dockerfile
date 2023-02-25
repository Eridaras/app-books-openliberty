FROM eclipse-temurin:17.0.5_8-jre-alpine
RUN mkdir /app
WORKDIR /app
COPY build/libs/app-books.jar ./app.jar
CMD ["java","-jar","app.jar"]