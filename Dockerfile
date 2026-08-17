FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY target/*.jar app.jar

EXPOSE 1010

ENTRYPOINT ["java", "-jar", "app.jar"]

