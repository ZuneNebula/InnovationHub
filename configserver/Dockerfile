FROM openjdk:11-jre-slim
WORKDIR usr/src
ADD ./target/configserver.jar /usr/src/configserver.jar
ENTRYPOINT ["java","-jar","configserver.jar"]