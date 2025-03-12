FROM eclipse-temurin:17-jdk
VOLUME /tmp
COPY target/service-registry.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]