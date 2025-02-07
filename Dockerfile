FROM maven:3.8-openjdk-17 AS build

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src /app/src

RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/target/produto-0.0.1-SNAPSHOT.jar /app/produto.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "/app/produto.jar"]
