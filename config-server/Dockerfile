FROM eclipse-temurin:17-jdk
VOLUME /tmp
COPY target/config-server.jar app.jar
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "/app.jar"]