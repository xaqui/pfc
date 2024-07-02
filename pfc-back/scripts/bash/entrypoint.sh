#!/bin/sh
# entrypoint.sh

set -e

echo "Running entrypoint.sh"

# Extract host and port from db:3306
host=db


# Wait for the database to be up
/app/wait-for-it.sh "$host" java -Dspring.profiles.active=docker -jar /app/back-0.0.1-SNAPSHOT.jar
