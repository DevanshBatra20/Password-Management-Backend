#
# Build stage
#
FROM maven:3.6.0-jdk-17-oracle AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM openjdk:17-oracle
COPY --from=build /home/app/target/passwordmanagementbackedn-0.0.1-SNAPSHOT.jar /usr/local/lib/demo.jar
EXPOSE 6939
ENTRYPOINT ["java","-jar","/usr/local/lib/demo.jar"]