FROM openjdk:11-jre-slim
WORKDIR usr/src
ENV MONGO_URI=mongodb://localhost:27017/innovationhub
ADD ./target/userservice.jar /usr/src/userservice.jar
ENTRYPOINT ["java","-jar","userservice.jar"]
