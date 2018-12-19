# SmartCamera
** Automatically identify our images **

_State: Beta_

This project is a small showcase for an app to digitize the business pictures. The application recognize barcodes and texts in images and extract this information. This allows images to be searched more quickly and specifically for the appropriate content.

## About the idea 

Every day we take hundreds of pictures to file as evidence. These pictures are usually only placed "flat" in a directory. If you now need a special photo, the big search starts.To make matters worse, the images must first be copied from the (mostly) digital cameras to the network drive.

Thus a lot of time is lost for copying and searching the pictures.
So why not use your existing smartphones for this task?

With this Showcase App you can take photos directly from your SmartPhone and transfer them online to the server. The images are automatically evaluated and analyzed there.

Since barcodes, QR codes or texts can be found on many of the images, these are evaluated immediately and linked to the image in the database. This not only makes filing easier, but also significantly accelerates the search for the desired image.

## The architecture
The application consists of three modules / two in execution:
- [CameraProcessor][1]: two Python scripts for analyzing the images.
- [CameraService][2]: a Spring Boot application (Java) that handle the processor and stores the images. The application also provides a REST API to retrieve the data.
- [CameraApp][3]: An Angular application that retrieves and displays the data via the REST service.

The prerequisites
The following software components are required:
- Python 2.x and PIP
- OpenCV
- Tesseract-OCR
- ZBar
- Java 1.8+
- Node.js and Angular

The installation can be carried out using the `setup.sh` scripts.

Optional you can use the Dockerfile to build containers for the execution.

## Features of the application

The application open in a modern web browser or on a mobile device. On start-screen you see the upload dialog. On desktop computer you can choose the image for upload. On a mobile device you can take a picture with your camera. Then you upload your image to the server.

![][image-1]

Then you can switch to the browse section. Here you find all your image and recent analysis. You can see how many information could be extracted from your image.

![][image-2]

If you select an image, you can see the extracted details from the images.

![][image-3]

If you scroll down you find two images. The first image shows the recognized barcodes and their content. The second image shows the original image from your upload.

![][image-4]

To make the application easier to use on the SmartPhone, it can be placed directly on the start screen as an app. This means it is always at hand when you need it.

![for iOS][image-5]

## Scripts
To build the entire application and the Docker image use this script:

	bash>build.sh

With the optional parameter `-skipDocker` you can skip the Docker build.

To run the local application server on port 4200 and the service on port 8080  you can use this script:

	bash>run.sh

With these both scripts you can install or uninstall the Docker container on your local system.

	bash>install.sh
	bash>uninstall.sh

[1]:	camera-processor
[2]:	camera-service
[3]:	camera-app

[image-1]:	documentation/TakeOrUploadAnImage.png "Take or upload an image"
[image-2]:	documentation/StoredImages.png "Stored images"
[image-3]:	DetalsForAnImage.png "Details for an image"
[image-4]:	documentation/DetectedBarcodes.png "Detected barcodes"
[image-5]:	documentation/AddToHome.png "Add to home screen"