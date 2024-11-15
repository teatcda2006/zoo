FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/zoo.jar .

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/zoo.jar"]
#ENTRYPOINT ["tail", "-f", "/dev/null"]