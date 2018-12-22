### STAGE 1: Build the app
FROM maven:3-jdk-11 as builder

# Building the app
WORKDIR /java-app
COPY . .
RUN mvn clean package

## STAGE 2: Setup the image
FROM ubuntu:18.04

# Update release
RUN apt-get update
RUN apt-get upgrade -y

# Install needed packages
RUN DEBIAN_FRONTEND=noninteractive apt-get install -y tzdata

RUN apt-get install -y openjdk-11-jre

RUN apt-get install -y python python-pip
RUN apt-get install -y python-opencv
RUN apt-get install -y libzbar0
RUN apt-get install -y tesseract-ocr tesseract-ocr-eng tesseract-ocr-deu 

# Install needed pip modules
RUN pip install pyzbar
RUN pip install pytesseract

# Copy the build
WORKDIR /
COPY --from=builder /java-app/target/camera-service.jar /
COPY --from=builder /java-app/target/*.py /

# Expose port
EXPOSE 8080

# Run service
ENTRYPOINT [ "/usr/bin/java", "-jar", "camera-service.jar", "--spring.profiles.active=production" ]

# For testing only
#COPY src/test/sample/Sample1.png /sample.png
#ENTRYPOINT [ "/usr/bin/python", "barcode-processor.py", "-i", "sample.png", "-o", "result.png", "-r", "result.json" ]
#ENTRYPOINT [ "/usr/bin/python", "text-processor.py", "-i", "sample.png", "-r", "result.text" ]
#ENTRYPOINT [ "bash" ]
