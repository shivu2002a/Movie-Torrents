#
# Build stage
# 
# FROM maven:3.8.2-jdk-11 AS build
# COPY . .
# RUN mvn clean package -Pprod -DskipTests

#
# Package stage
#
# FROM openjdk:11
# ADD target/yifyapp.jar yifyapp.jar
# COPY --from=build /target/yifyapp-0.0.1-SNAPSHOT.jar yifyapp.jar
# ENV PORT=8080
# EXPOSE 8082
# ENTRYPOINT ["java","-jar","/yifyapp.jar"]

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
COPY --from=build /target/yifyapp-0.0.1-SNAPSHOT.jar yifyapp.jar
# ENV PORT=8080
EXPOSE 8082
ENTRYPOINT ["java","-jar","/yifyapp.jar"]