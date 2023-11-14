FROM openjdk:17
ARG Jar_FILE=target/*.jar
COPY ${Jar_FILE} service.jar
ENTRYPOINT ["java","-jar","service.jar"]
EXPOSE 9102