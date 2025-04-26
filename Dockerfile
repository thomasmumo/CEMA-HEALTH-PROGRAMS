FROM openjdk:17-jdk

EXPOSE 8080
ADD target/cema.jar cema.jar
ENTRYPOINT ["java", "-jar", "/cema.jar"]