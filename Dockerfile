FROM openjdk:11-alpine
COPY target/pruebaneoris-0.0.1-SNAPSHOT.jar pruebaneoris-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","pruebaneoris-0.0.1-SNAPSHOT.jar"]

//docker build --tag=message-server:latest 
//docker run -p8887:5000 message-server:latest