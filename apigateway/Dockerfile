FROM openjdk:11-jre-slim
WORKDIR usr/src
ADD ./target/apigateway.jar /usr/src/apigateway.jar
# RUN ls /usr/src
ENTRYPOINT ["java","-jar","apigateway.jar"]