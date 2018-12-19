#!/bin/bash
#
# Setup the needed libaries for development on linux.
# The script is testet with Ubuntu 18.04.
#
# The Docker installation is not included in this script.
#
# You need sudo rights for execute this script.
# 

# Update the system
sudo apt-get update
sudo apt-get upgrade -y

# Install needed packages
sudo apt-get install -y openjdk-11-jre

sudo apt-get install -y python python-pip
sudo apt-get install -y python-opencv
sudo apt-get install -y libzbar0
sudo apt-get install -y tesseract-ocr tesseract-ocr-eng tesseract-ocr-deu 

# Install needed pip modules
sudo pip install pyzbar
sudo pip install pytesseract

echo Please install Docker - more https://docs.docker.com/install/linux/docker-ce/ubuntu/
