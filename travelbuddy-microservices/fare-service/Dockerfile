FROM openjdk:17

LABEL maintainer="junbaek1004@gmail.com"

WORKDIR /app

COPY target/fare-service-0.0.1-SNAPSHOT.jar fare-service.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "fare-service.jar"]