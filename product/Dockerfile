FROM openjdk:22-jdk

WORKDIR /app

COPY target/product-0.0.1-SNAPSHOT.jar /app/product-service.jar

ENTRYPOINT ["java", "-jar", "/app/product-service.jar"]