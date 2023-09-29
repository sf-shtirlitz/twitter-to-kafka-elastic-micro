#FROM eclipse-temurin:20.0.2_9-jdk-alpine
FROM amazoncorretto:20-al2-generic-jdk

LABEL maintainer="shtirlitz@gmail.com"

WORKDIR /app

COPY target/elastic-query-service-0.0.1-SNAPSHOT.jar /app/elastic-query-service.jar

ENTRYPOINT ["java", "-jar", "elastic-query-service.jar"]