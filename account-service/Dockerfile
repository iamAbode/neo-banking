FROM eclipse-temurin:17-jdk
VOLUME /tmp
COPY target/account-service.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "/app.jar"]