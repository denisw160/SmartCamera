#!/bin/bash
#
# Running the complete application.
#

WORK_DIR=$(pwd)

# Run the camera-service
cd $WORK_DIR/camera-service
nohup ./run.sh &>/dev/null &

# Run the camera-app
cd $WORK_DIR/camera-app
nohup ./run.sh &>/dev/null &

echo Running jobs
jobs

read -p "Press any key to kill... " -n1 -s
disown
pkill -f 'ng serve --proxy-config proxy.json' 
pkill -f 'java -jar camera-service.jar'
