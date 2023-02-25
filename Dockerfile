FROM eclipse-temurin:17.0.5_8-jre-alpine
RUN mkdir /app
WORKDIR /app
COPY build/libs/app-book-1.0-SNAPSHOT.jar ./app-book-1.0-SNAPSHOT.jar
EXPOSE 9080
ENV MONGO_DB_URL=mongodb://mongo:27017/distribuida
CMD ["java","-jar","app-book-1.0-SNAPSHOT.jar"]

