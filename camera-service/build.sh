#!/bin/bash
#
# Building the service and prepare the Docker images.
#
# User must have access to Docker.
#
# Usage: ./build.sh
# 

NAME=camera-service
TAG=latest

# Copy external files 
echo Update processor files
cp ../camera-processor/src/*.py src/main/py/

# Remove unused images
#echo Remove unused images
#docker image prune -a -f

# Building the image
echo Building the image
docker build -t $NAME:$TAG .

# Running the container
#echo Running the container on port 8889
#docker run --rm -it --name camera-service -p 8889:8080 $NAME:$TAG 
