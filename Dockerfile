#
# Build stage
#
FROM maven:3.8.2-jdk-11 AS build
COPY . .
RUN mvn clean package -Pprod -DskipTests

#
# Package stage
#
FROM openjdk:11-jdk-slim
COPY --from=build /target/magtor-0.0.1-SNAPSHOT.jar magtor.jar
# ENV PORT=8080
EXPOSE 8082
ENTRYPOINT ["java","-jar","magtor.jar"]