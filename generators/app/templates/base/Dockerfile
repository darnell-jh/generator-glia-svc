FROM openjdk:8-jdk-alpine

VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE:-build/libs/<%=serviceName%>-1.0-SNAPSHOT.jar} app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
