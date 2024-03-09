FROM maven:3.9-amazoncorretto-17
COPY . .
RUN mvn clean package -DskipTests

FROM amazoncorretto:17
COPY --from=0 /target/project-0.0.1-SNAPSHOT.jar project.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "project.jar"]