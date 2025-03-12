FROM eclipse-temurin:17-jdk
VOLUME /tmp
COPY target/account-service.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]