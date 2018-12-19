#!/bin/bash
#
# Building the service and prepare the Docker images.
#
# User must have access to Docker.
#
# Usage: ./build.sh [-skipDocker]
#  - with -skipDocker you can skip the Docker build
# 

NAME=camera-service
TAG=latest

# Building the service
echo Building the service
./mvnw clean package

# Building Docker
if [ "$1" != "-skipDocker" ]
    then
        # Remove unused images
        #echo Remove unused images
        #docker image prune -a -f

        # Building the image
        echo Building the image
        docker build -t $NAME:$TAG .

        # Running the container
        #echo Running the container on port 8889
        #docker run --rm -it --name camera-service -p 8889:8080 $NAME:$TAG 
fi