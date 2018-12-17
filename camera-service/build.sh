#!/bin/bash
#
# Building the service and prepare the Docker images.
#
# User must have access to Docker.
#

NAME=camera-service
TAG=LATEST

# Building the service
echo Building the service
mvn clean package

# Remove unused images
echo Remove unused images
docker image prune -a -f

# Building the image
echo Building the image
docker build -t $NAME:$TAG .

# Running the container
echo Running the container on port 8888
docker run --rm -it -p 8888:8080 $NAME:$TAG 
