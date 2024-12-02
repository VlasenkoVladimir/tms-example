FROM openjdk:17
ARG jarFile=build/libs/taskManagementSystem-0.0.1-SNAPSHOT.jar
WORKDIR /opt/app
COPY ${jarFile} taskManagementSystem.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "taskManagementSystem.jar"]