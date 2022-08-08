FROM openjdk:11-jre-slim
WORKDIR usr/src
ADD ./target/notificationservice.jar /usr/src/notificationservice.jar
ENTRYPOINT ["java","-jar","notificationservice.jar"]
