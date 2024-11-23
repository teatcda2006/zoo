FROM maven:3.9.8-eclipse-temurin-21 AS builder
WORKDIR /app

COPY . /app/.


RUN --mount=type=cache,target=/root/.m2 mvn clean package  -Dmaven.test.skip


FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar /app/*.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/*.jar"]

# FROM maven:3.9.5-eclipse-temurin-21 AS builder
#
# WORKDIR /build
#
# COPY pom.xml .
# COPY src ./src
#
# RUN mvn clean package -DskipTests
#
# FROM openjdk:21-jdk-slim
#
# WORKDIR /app
#
# COPY --from=builder /build/target/*.jar *.jar
#
# EXPOSE 8080
#
# CMD ["java", "-jar", "*.jar"]


# FROM openjdk:21-jdk-slim
#
# WORKDIR /app
#
# COPY target/zoo.jar .
#
# EXPOSE 8080
#
# ENTRYPOINT ["java", "-jar", "/app/zoo.jar"]


#ENTRYPOINT ["tail", "-f", "/dev/null"]