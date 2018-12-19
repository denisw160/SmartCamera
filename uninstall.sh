#!/bin/bash
#
# Stop und remove the container.
#
# User must have access to Docker.
#

PREFIX=smartcamera

# Stopping and removing container
echo Stopping container ...
docker stop $PREFIX-app
docker stop $PREFIX-service

echo Removing container ...
docker rm $PREFIX-app
docker rm $PREFIX-service
