FROM ubuntu/jre:17-22.04_edge
COPY /target/flutter-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080

