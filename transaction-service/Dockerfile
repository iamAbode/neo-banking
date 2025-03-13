FROM eclipse-temurin:17-jdk
VOLUME /tmp
COPY target/transaction-service.jar app.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "/app.jar"]