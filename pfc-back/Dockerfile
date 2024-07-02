FROM maven:3.8.7-eclipse-temurin-19 as build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests -Pdocker

# Use an OpenJDK 17 image to run the application
FROM eclipse-temurin:19
WORKDIR /app
COPY --from=build /app/target/back-0.0.1-SNAPSHOT.jar /app/back-0.0.1-SNAPSHOT.jar

# Install mysql-client to provide mysqladmin
RUN apt-get update && apt-get install -y mysql-client && \ 
    rm -rf /var/lib/apt/lists/*

RUN  apt-get update && apt-get install dos2unix && \
    apt-get clean

COPY scripts/bash/wait-for-it.sh /app/wait-for-it.sh
COPY scripts/bash/entrypoint.sh /app/entrypoint.sh
RUN dos2unix /app/wait-for-it.sh
RUN dos2unix /app/entrypoint.sh
RUN chmod +x /app/wait-for-it.sh
RUN chmod +x /app/entrypoint.sh
ENTRYPOINT ["/app/entrypoint.sh"]


EXPOSE 8080
