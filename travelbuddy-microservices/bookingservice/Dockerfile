
FROM openjdk:17




WORKDIR /app


COPY target/bookingservice-0.0.1-SNAPSHOT.jar booking-service.jar


EXPOSE 8082


ENTRYPOINT ["java", "-jar", "booking-service.jar"]