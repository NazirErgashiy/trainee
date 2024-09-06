FROM openjdk:11-jdk-slim
ADD target/trainee-0.0.1-SNAPSHOT.jar /usr/local/app.jar
ENTRYPOINT ["java", "-jar", "/usr/local/app.jar","--spring.profiles.active=container"]