#!/bin/bash

# Pull new changes
git pull

# Prepare Jar
mvn clean
mvn package

# Ensure, that docker-compose stopped
docker-compose stop

# Add environment variables
export BOT_NAME=test_JavaAcorn_bot
export BOT_TOKEN=5578041606:AAHHXNOU4w82W1QhOR7lqfjUVTnqSpV6qvU
export BOT_DB_USERNAME=botusername
export BOT_DB_PASSWORD=botpassword

# Start new deployment
docker-compose up --build -d
mvn spring-boot:run