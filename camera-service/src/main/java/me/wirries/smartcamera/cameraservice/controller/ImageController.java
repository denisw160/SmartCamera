package me.wirries.smartcamera.cameraservice.controller;

import me.wirries.smartcamera.cameraservice.model.ImageData;
import me.wirries.smartcamera.cameraservice.repository.ImageDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * This controller returns the image from the data.
 *
 * @author denisw
 * @version 1.0
 * @since 2018-12-16
 */
@Controller
public class ImageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageController.class);

    private final ImageDataRepository dataRepository;

    /**
     * Constructor with AutoWiring the dependencies.
     */
    @Autowired
    public ImageController(ImageDataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    /**
     * This method returns the image for the given idImage.
     *
     * @param idImage id of the image
     * @return image as binary stream
     */
    @ResponseBody
    @RequestMapping(value = "/image/{idImage}", method = RequestMethod.GET)
    public byte[] getImage(@PathVariable String idImage, HttpServletResponse response) throws IOException {
        LOGGER.debug("Query for image id {}", idImage);
        Optional<ImageData> data = dataRepository.findById(idImage);

        if (!data.isPresent()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return null;
        } else {
            ImageData imageData = data.get();
            response.setContentType(imageData.getMediaType());
            return imageData.getImage();
        }
    }

}