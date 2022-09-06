FROM openjdk:11

COPY target/PowerSystem-0.0.1-SNAPSHOT.jar PowerSystem-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "/PowerSystem-0.0.1-SNAPSHOT.jar"]