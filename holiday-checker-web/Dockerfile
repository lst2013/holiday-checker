FROM openjdk:latest
ADD /target/holiday-checker.jar holiday-checker.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "holiday-checker.jar"]