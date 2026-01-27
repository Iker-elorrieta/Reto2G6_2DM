# Dockerfile para elorserv (Spring Boot)
FROM eclipse-temurin:21-jdk

WORKDIR /app


COPY . /app
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

EXPOSE 8080
EXPOSE 5000

CMD ["java", "-jar", "target/elorserv-0.0.1-SNAPSHOT.jar"]
