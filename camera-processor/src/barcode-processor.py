import argparse

import cv2
from pyzbar import pyzbar

#
# This scripts is the processor for the barcodes.
# The processor detects all bar- and qrcodes in the given image.
# All readed barcodes will be written in the outputfile. The format of
# the output is a JSON file.
#
# Idea from: https://www.pyimagesearch.com/2018/05/21/an-opencv-barcode-and-qr-code-scanner-with-zbar/
#
# require modules
#  - opencv-python + pyzbar(pip)
#  - opencv+ zbar (apt)
#

# construct the argument parser and parse the arguments
ap = argparse.ArgumentParser()
ap.add_argument("-i", "--input", required=True, help="path to input image")
ap.add_argument("-o", "--output", required=True, help="path to output image")
ap.add_argument("-r", "--result", required=True, help="path to result file")
ap.add_argument("-d", "--display", action='store_true', required=False, help="display the result")
args = vars(ap.parse_args())

# load the input image
image = cv2.imread(args["input"])

# open result file and init json array
result = open(args["result"], "w")
result.write("[\n")

# find the barcodes in the image and decode each of the barcodes
barcodes = pyzbar.decode(image)

# loop over the detected barcodes
i = 0
for barcode in barcodes:
    # extract the bounding box location of the barcode and draw the
    # bounding box surrounding the barcode on the image
    (x, y, w, h) = barcode.rect
    cv2.rectangle(image, (x, y), (x + w, y + h), (0, 0, 255), 2)

    # the barcode data is a bytes object so if we want to draw it on
    # our output image we need to convert it to a string first
    barcodeData = barcode.data.decode("utf-8")
    barcodeType = barcode.type

    # draw the barcode data and barcode type on the image
    text = "{} ({})".format(barcodeData, barcodeType)
    cv2.putText(image, text, (x, y - 10), cv2.FONT_HERSHEY_SIMPLEX, 0.5, (0, 0, 255), 2)

    # print the barcode type and data to the terminal
    print("[INFO] Found {} barcode: {}".format(barcodeType, barcodeData))

    # store barcode in result file
    result.write("\t")
    if i > 0:
        result.write(",")
    result.write("{\"type\": \"" + barcodeType + "\", \"data\": \"" + barcodeData + "\"}\n")
    i = i + 1

# write the image
cv2.imwrite(args["output"], image)

# close json array
result.write("]\n")

# show the output image
if args["display"]:
    cv2.imshow("Result", image)
    cv2.waitKey(0)
