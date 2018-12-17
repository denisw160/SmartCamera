#!/bin/bash
#
# Building the complete application and prepare the Docker images.
#
# User must have access to Docker.
#

WORK_DIR=$(pwd)

# Build the camera-service
cd $WORK_DIR/camera-service
./build.sh

# Build the camera-app
cd $WORK_DIR/camera-app
./build.sh

#NAME=camera-app
#TAG=LATEST

# Remove old build
#echo Remove old build
#rm -rf dist

# Building the app
#echo Building the app
#npm install
#ng build --prod

# Remove unused images
#echo Remove unused images
#docker image prune -a -f

# Building the image
#echo Building the image
#docker build -t $NAME:$TAG .

# Running the container
#echo Running the container on port 8888
#docker run --rm -it -p 8888:80 $NAME:$TAG 
