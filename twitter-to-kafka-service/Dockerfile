#FROM gcr.io/buildpacks/builder:v1
FROM eclipse-temurin:20.0.2_9-jdk-alpine
#FROM paketobuildpacks/builder:base
#FROM heroku/buildpacks:18


LABEL maintainer="shtirlitz@gmail.com"

WORKDIR /app

COPY target/twitter-to-kafka-service-0.0.1-SNAPSHOT.jar /app/twitter-to-kafka-service.jar

ENTRYPOINT ["java", "-jar", "twitter-to-kafka-service.jar"]

