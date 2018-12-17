#!/bin/bash
#
# Running the service. The port is 8080.
#

# Building the service
echo Building the service
mvn clean package

# Running the service
echo Running the service
cd target
java -jar camera-service.jar
