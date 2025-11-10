# Etapa 1: construir la app con Maven
FROM eclipse-temurin:17-jdk AS builder
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

# Etapa 2: ejecutar la app
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=builder /app/target/transferbanc-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENV PORT=8080
ENTRYPOINT ["java", "-jar", "app.jar"]
