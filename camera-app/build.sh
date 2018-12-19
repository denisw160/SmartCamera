#!/bin/bash
#
# Building the Angular App and the Docker image
#
# The build for the app is started by angular cli.
# Release files for the app stored in ./dist
#
# User must have access to Docker.
#
# Usage: ./build.sh [-skipDocker]
#  - with -skipDocker you can skip the Docker build
# 

NAME=camera-app
TAG=LATEST

# Remove old build
echo Remove old build
rm -rf dist

# Building the app
echo Building the app
npm install
ng build --prod

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
        # Container needs the camera-service as linked container with name apiserver
        #echo Running the container on port 8888
        #docker run --rm -it --name camera-app --link camera-service:apiserver -p 8888:80 $NAME:$TAG 
fi
