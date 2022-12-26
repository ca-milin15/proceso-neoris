FROM openjdk:17-alpine
COPY target/pruebaneoris-0.0.1-SNAPSHOT.jar pruebaneoris-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","pruebaneoris-0.0.1-SNAPSHOT.jar"]
