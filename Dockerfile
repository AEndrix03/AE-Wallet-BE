FROM maven:3.9.5-openjdk-21 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:21-jdk-slim
WORKDIR /app

COPY --from=build /app/target/aewallet-0.0.1-SNAPSHOT.jar app.jar

# Espone la porta che verrà utilizzata dall'applicazione
EXPOSE 8080

# Comando per eseguire l'applicazione
ENTRYPOINT ["java", "-jar", "app.jar"]