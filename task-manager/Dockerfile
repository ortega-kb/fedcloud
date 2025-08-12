FROM amazoncorretto:17.0.3
VOLUME /tmp
ARG JAR_FILE=target/*.jar
EXPOSE 8001
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]