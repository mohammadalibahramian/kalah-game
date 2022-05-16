FROM openjdk:11-jre-slim
COPY target/kalah-0.0.1-SNAPSHOT.jar kalah-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/kalah-0.0.1-SNAPSHOT.jar"]