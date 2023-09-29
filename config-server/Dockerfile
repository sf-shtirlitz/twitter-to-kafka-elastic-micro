#FROM paketobuildpacks/builder:base
FROM eclipse-temurin:20.0.2_9-jdk-alpine

LABEL maintainer="shtirlitz@gmail.com"

WORKDIR /app

COPY target/config-server-0.0.1-SNAPSHOT.jar /app/config-server.jar

ENTRYPOINT ["java", "-jar", "config-server.jar"]

