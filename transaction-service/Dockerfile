FROM eclipse-temurin:17-jdk
VOLUME /tmp
COPY target/transaction-service.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]