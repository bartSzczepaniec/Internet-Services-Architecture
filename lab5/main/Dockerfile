FROM openjdk:11-jdk
COPY lab3_kategorie/target/lab1-0.0.1-SNAPSHOT.jar appk.jar

COPY lab3_elementy/target/lab1-0.0.1-SNAPSHOT.jar appe.jar

COPY lab3_gateway/target/lab1-0.0.1-SNAPSHOT.jar appg.jar

COPY starter.sh starter.sh

EXPOSE 8081

ENTRYPOINT ["sh","/starter.sh"]