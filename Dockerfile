FROM maven:3.9.8-eclipse-temurin-21 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:21-jdk-slim
WORKDIR /app

COPY --from=build /app/target/aewallet-0.0.1-beta1.jar aewallet.jar

EXPOSE 8443

ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "-jar", "aewallet.jar"]
