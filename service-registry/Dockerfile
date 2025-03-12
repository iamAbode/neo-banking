FROM eclipse-temurin:17-jdk
VOLUME /tmp
COPY target/service-registry.jar app.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "/app.jar"]