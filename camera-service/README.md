## CameraService

### Description
This module provides the backend for the SmartCamera. The Spring Boot application create a REST api for query and upload the images. The images will stored in a H2 in-memory-database. The service execute for all images the both processors and get the extracted data for text and barcodes.

### Scripts
To build the application and the Docker image use this script.

	bash>build.sh

With the optional parameter `-skipDocker` you can skip the Docker build.

To run the local server on Port 8080 you can use this script:

	bash>run.sh
