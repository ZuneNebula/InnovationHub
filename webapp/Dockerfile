FROM openjdk:11-jre-slim
WORKDIR usr/src
ADD ./target/webapp.jar /usr/src/webapp.jar
ENTRYPOINT ["java","-jar","webapp.jar"]