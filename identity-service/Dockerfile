FROM eclipse-temurin:17-jdk
VOLUME /tmp
COPY target/identity-service.jar app.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "/app.jar"]