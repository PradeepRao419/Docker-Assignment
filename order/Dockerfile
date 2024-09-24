FROM openjdk:22-jdk

WORKDIR /app

COPY target/order-0.0.1-SNAPSHOT.jar /app/order-service.jar

ENTRYPOINT ["java", "-jar", "/app/order-service.jar"]