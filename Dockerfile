FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY target/hello-1.0-SNAPSHOT.jar app.jar

EXPOSE 9090

ENTRYPOINT ["java", "-jar", "app.jar"]
