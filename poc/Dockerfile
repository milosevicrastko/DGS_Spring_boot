FROM gradle:8.4-jdk21 as builder

WORKDIR /app

COPY build.gradle settings.gradle /app/
COPY gradle /app/gradle

COPY . /app/

RUN gradle :build -x test

FROM openjdk:21-jdk-slim

WORKDIR /app

COPY --from=builder /app/build/libs/poc-0.0.1-SNAPSHOT.war /app/app.war

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.war"]
