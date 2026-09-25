FROM maven:3.9.11-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -q -DskipTests dependency:go-offline
COPY src src
RUN mvn -q -DskipTests package

FROM eclipse-temurin:17-jre-jammy
RUN apt-get update && apt-get install -y --no-install-recommends curl && rm -rf /var/lib/apt/lists/*
WORKDIR /app
COPY --from=build /app/target/companages-1.0.0.jar app.jar
EXPOSE 8080
HEALTHCHECK --interval=15s --timeout=5s --retries=5 CMD curl -fsS http://localhost:8080/actuator/health || exit 1
ENTRYPOINT ["java","-jar","app.jar"]
