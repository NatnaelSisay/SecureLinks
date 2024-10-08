FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/SecureNotes-0.0.1-SNAPSHOT.jar /app/securenote.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "securenote.jar"]