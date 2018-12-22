#!/bin/bash
#
# Building the Angular App and the Docker image
#
# The build for the app is started by angular cli.
# Release files for the app stored in ./dist
#
# User must have access to Docker.
#
# Usage: ./build.sh [/yourPath/]
#  - setup the basehref to /yourPath/ in your Angular app
# 

NAME=camera-app
TAG=latest

BASEHREF=/

if [ -n "$1" ]; then
    BASEHREF=$1
fi

# Remove unused images
#echo Remove unused images
#docker image prune -a -f

# Building the image
echo Building the image
docker build --build-arg basehref=$BASEHREF -t $NAME:$TAG .

# Running the container
# Container needs the camera-service as linked container with name apiserver
#echo Running the container on port 8888
#docker run --rm -it --name camera-app --link camera-service:apiserver -p 8888:80 $NAME:$TAG 
