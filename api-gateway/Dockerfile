FROM eclipse-temurin:17-jdk
VOLUME /tmp
COPY target/api-gateway.jar app.jar
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "/app.jar"]