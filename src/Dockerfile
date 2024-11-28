FROM openjdk:17
ARG jarFile=build/libs/taskManagementSystem-0.1.war
WORKDIR /opt/app
COPY ${jarFile} taskManagementSystem.war
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "taskManagementSystem.war"]