## CameraProcessor

### Description
This module provides the python script for analyzing the images. The scripts uses OpenCV, Tesseract and ZBar for reading text and barcodes.

These scripts are integrated in the CameraService, but you can execute the also standalone.

### Requirements
For executing and development with these scripts you need the following components and modules.

OpenCV for Python 2.x

bash\>sudo apt-get install -y python-opencv

ZBar

bash\>sudo apt-get install -y libzbar0
bash\>sudo pip install pyzbar

Tesseract-OCR

bash\>sudo apt-get install -y tesseract-ocr tesseract-ocr-eng tesseract-ocr-deu
bash\>sudo pip install pytesseract

For a simple setup you find in the root directory a script `setup.sh` for installing these modules on your Ubuntu system.  
