# backend/Dockerfile
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY . .
RUN rm -rf /var/lib/apt/lists/* && apt-get update

RUN apt-get update && apt-get install -y gnupg
RUN apt-get update && apt-get install -y findutils
RUN ./gradlew build -x test
CMD ["java", "-jar", "build/libs/demo-0.0.1-SNAPSHOT.jar"]
