# ---------- Frontend Build ----------
FROM node:20-slim AS frontend-builder

WORKDIR /app

# Copy frontend code
COPY src/main/java/com/scone/DeCypher/frontend ./frontend

WORKDIR /app/frontend

# Install dependencies and build
RUN npm ci
RUN npm run build

# ---------- Backend Build ----------
FROM maven:3.9-eclipse-temurin-17 AS backend-builder

WORKDIR /app

# Copy only what we need for dependency resolution first (caching)
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw mvnw
RUN chmod +x mvnw
RUN ./mvnw dependency:resolve

# Copy rest of the backend source
COPY src ./src

# Copy frontend build output into Spring Boot's static resources
COPY --from=frontend-builder /app/frontend/dist/ src/main/resources/static/

# Build the Spring Boot app
RUN ./mvnw clean package -DskipTests

# ---------- Runtime ----------
FROM eclipse-temurin:17-jdk-jammy AS runtime

WORKDIR /app

# Copy JAR from build stage
COPY --from=backend-builder /app/target/*.jar app.jar

# Run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]