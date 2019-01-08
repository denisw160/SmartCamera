#!/bin/bash
#
# Upload all files from a directory to the service.
#
# Usage: ./upload.sh [server url, e.g. http://localhost:4200/api/upload] [upload folder, e.g. ./test] [user - optional] [password - optional] 
# 

URL=http://localhost:4200/api/upload
FOLDER=./camera-processor/test
USER=
PASSWORD=

if [ -n "$1" ]; then
    URL=$1
fi

if [ -n "$2" ]; then
    FOLDER=$2
fi

if [ -n "$3" ]; then
    USER=$3
fi

if [ -n "$4" ]; then
    PASSWORD=$4
fi

echo "Upload files from folder $FOLDER to $URL"
if [ -n "$USER" ]; then 
    echo "Using user $USER for login"
fi

for f in $FOLDER/*.*; do
    echo ""
    echo "Upload file $f"
    if [ -n "$USER" ]; then 
        curl --user $USER:$PASSWORD -X POST -F 'upload={"name": "'"${f##*/}"'"}' -F 'uploadFile=@"'"$f"'"' $URL
    else
        curl -X POST -F 'upload={"name": "'"${f##*/}"'"}' -F 'uploadFile=@"'"$f"'"' $URL
    fi
    echo ""
done
