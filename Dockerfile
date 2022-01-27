FROM openjdk:11-jdk-slim
ARG JAR_FILE=build/libs/api-football-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","/app.jar"]