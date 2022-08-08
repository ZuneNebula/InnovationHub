FROM openjdk:11-jre-slim
WORKDIR usr/src
ENV MONGO_URI=mongodb://localhost:27017/innovationhub
ADD ./target/innovationmanagement.jar /usr/src/innovationmanagement.jar
ENTRYPOINT ["java","-jar","innovationmanagement.jar"]
