# Image de base Java 21 (JRE seulement : plus légère)
FROM eclipse-temurin:21-jre

WORKDIR /app

# Utilisateur non-root : plus sûr que d'exécuter en root
RUN useradd --system --no-create-home appuser

# Le JAR doit exister avant : cd backend && ./mvnw -B clean package
COPY backend/target/*.jar app.jar

USER appuser
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
