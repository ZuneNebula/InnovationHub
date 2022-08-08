FROM openjdk:11-jre-slim
WORKDIR usr/src
ADD ./target/discovery.jar /usr/src/discovery.jar
# ENV JAVA_OPTS=''
# ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
ENTRYPOINT ["java","-jar","discovery.jar"]