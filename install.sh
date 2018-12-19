#!/bin/bash
#
# Build and install the Docker images.
#
# Only the camera-app has external access on port 10015.
#
# User must have access to Docker.
#
# Usage: ./build.sh [-skipBuild]
#  - with -skipBuild you can skip the build
#

PREFIX=smartcamera
TAG=latest

PORT=10015

SERVICE=camera-service
APP=camera-app

WORK_DIR=$(pwd)

if [ "$1" != "-skipBuild" ]
    then
        # Build the camera-service
        cd $WORK_DIR/camera-service
        ./build.sh

        # Build the camera-app
        cd $WORK_DIR/camera-app
        ./build.sh
fi

# Remove old container
cd $WORK_DIR
./uninstall.sh

# Install the container
echo Install the $SERVICE
docker run -d \
    --restart always \
    --name $PREFIX-service \
    $SERVICE:$TAG

echo Install the $APP
docker run -d \
    --restart always \
    --name $PREFIX-app \
    --link $PREFIX-service:apiserver \
    -p $PORT:80 \
    $APP:$TAG

echo Open the application on http://$(hostname):$PORT
