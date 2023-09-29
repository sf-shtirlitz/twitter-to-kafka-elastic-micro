#FROM eclipse-temurin:20.0.2_9-jdk-alpine
FROM amazoncorretto:20-al2-generic-jdk

LABEL maintainer="shtirlitz@gmail.com"

WORKDIR /app

COPY target/kafka-to-elastic-service-0.0.1-SNAPSHOT.jar /app/kafka-to-elastic-service.jar

ENTRYPOINT ["java", "-jar", "kafka-to-elastic-service.jar"]

