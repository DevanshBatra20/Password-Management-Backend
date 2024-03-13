FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
copy src/ src/
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
WORKDIR /app
COPY --from=build /app/target/classes /app/classes
COPY --from=build /app/target/password-service-0.0.1-SNAPSHOT.jar /app/password-service.jar
RUN mkdir -p /app/classes/com/example/passwordservice* /app/classes/com/example/passwordservice/
EXPOSE 8081
ENTRYPOINT ["java","-jar","password-service.jar"]