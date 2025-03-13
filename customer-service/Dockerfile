FROM eclipse-temurin:17-jdk
VOLUME /tmp
COPY target/customer-service.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app.jar"]