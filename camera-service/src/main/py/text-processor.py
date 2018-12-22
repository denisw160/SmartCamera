import argparse
import codecs
import os

import cv2
import numpy as np
import pytesseract

# from PIL import Image

#
# This scripts is the processor for the ocr/text.
# The processor detects all texts in the given image (input file).
# All texts will be stored in the result file. The format is a simple text file.
#
#
# Idea from:
# https://ampersandacademy.com/tutorials/python-data-science/recognise-text-and-digit-from-the-image-with-python-opencv-and-tesseract-ocr
#
# require modules
#  - opencv-python + pytesseract(pip)
#  - opencv (opencv-python) + tesseract (apt)
#

# construct the argument parser and parse the arguments
ap = argparse.ArgumentParser()
ap.add_argument("-i", "--input", required=True, help="path to input image")
ap.add_argument("-r", "--result", required=True, help="path to result file")
ap.add_argument("-b", "--blackWhite", action='store_true', required=False,
                help="apply threshold to get image with only black and white")
args = vars(ap.parse_args())

print "Running TextProcessor Version 1.0"
print " -input:", args["input"]
print " -result:", args["result"]
print " -blackWhite:", args["blackWhite"]
print ""

# test if input exists
if not os.path.isfile(args["input"]):
    print "File not found"
    exit(1)

# Read image with opencv
img = cv2.imread(args["input"])

# Convert to gray
img = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)

# Apply dilation and erosion to remove some noise
kernel = np.ones((1, 1), np.uint8)
img = cv2.dilate(img, kernel, iterations=1)
img = cv2.erode(img, kernel, iterations=1)

# Write image after removed noise
# cv2.imwrite("tmp_removed_noise.png", img)

#  Apply threshold to get image with only black and white
if args["blackWhite"]:
    img = cv2.adaptiveThreshold(img, 255, cv2.ADAPTIVE_THRESH_GAUSSIAN_C, cv2.THRESH_BINARY, 31, 2)

# Write the image after apply opencv to do some ...
# cv2.imwrite("tmp_thres.png", img)

# Recognize text with tesseract for python
# result = pytesseract.image_to_string(Image.open("tmp_thres.png"))
result = pytesseract.image_to_string(img)

# open result file and init json array
with codecs.open(args["result"], 'w', encoding='utf8') as f:
    f.write(result)

print ("Processing done")

exit(0)
