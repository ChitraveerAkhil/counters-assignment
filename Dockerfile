FROM openjdk:8-jre-alpine as openjdk
RUN mkdir -p /app
ADD target/counters-assignment-0.0.1-SNAPSHOT.jar /app/counters.jar
WORKDIR /app
EXPOSE 5000
CMD ["java", "-jar","/app/counters.jar"]