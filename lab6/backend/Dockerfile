FROM openjdk:11-jdk
COPY lab2/target/lab1-0.0.1-SNAPSHOT.jar app.jar

RUN mkdir uploads
CMD ["java","-jar", "app.jar"]

EXPOSE 8080