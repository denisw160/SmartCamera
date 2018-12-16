package me.wirries.smartcamera.cameraservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.wirries.smartcamera.cameraservice.model.*;
import me.wirries.smartcamera.cameraservice.repository.ImageDataRepository;
import me.wirries.smartcamera.cameraservice.repository.ImageDetailRepository;
import me.wirries.smartcamera.cameraservice.repository.ImageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

/**
 * This is the REST controller for the api of the camera service.
 * It's provides the access to the data.
 *
 * @author denisw
 * @version 1.0
 * @since 2018-12-16
 */
@RestController
@RequestMapping("/api")
public class ApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiController.class);

    private final ImageRepository imageRepository;
    private final ImageDataRepository dataRepository;
    private final ImageDetailRepository detailRepository;

    /**
     * Constructor with AutoWiring the dependencies.
     */
    @Autowired
    public ApiController(ImageRepository imageRepository,
                         ImageDataRepository dataRepository,
                         ImageDetailRepository detailRepository) {

        this.imageRepository = imageRepository;
        this.dataRepository = dataRepository;
        this.detailRepository = detailRepository;
    }

    /**
     * Load the images from the database and return the entries as a list.
     *
     * @return List of images
     */
    @GetMapping("/image")
    public List<Image> getImages() {
        LOGGER.debug("Loading all images");
        return imageRepository.findAll();
    }

    /**
     * Load the details for the given image and return the details as al list.
     *
     * @param idImage id of the image
     * @return List of details
     */
    @GetMapping("/image/{idImage}/detail")
    public List<ImageDetail> getDetails(@PathVariable String idImage) {
        LOGGER.debug("Loading all details for {}", idImage);
        return detailRepository.findAllByIdImageOrderByMeta(idImage);
    }

    /**
     * Upload the image from the client and store in database.
     *
     * @param uploadDescription JSON string with the description of the upload
     * @param uploadFile        Binary file
     * @return Response of the upload
     */
    @PostMapping(value = "/upload", consumes = {"multipart/form-data"})
    public UploadResponse upload(@RequestPart("upload") String uploadDescription,
                                 @RequestPart("uploadFile") MultipartFile uploadFile) {

        LOGGER.debug("Received upload {}", uploadDescription);
        LOGGER.debug("Received uploadFile {}", uploadFile);

        try {
            ObjectMapper mapper = new ObjectMapper();
            Upload upload = mapper.readValue(uploadDescription, Upload.class);

            Image image = new Image();
            image.setName(upload.getName());
            image.setTimestamp(new Date());
            image.setProcessed(false);
            image.setTags(0);

            imageRepository.save(image);
            LOGGER.debug("New image has id {}", image.getId());

            ImageData data = new ImageData();
            data.setIdImage(image.getId());
            data.setMediaType(uploadFile.getContentType());
            data.setImage(uploadFile.getBytes());
            dataRepository.save(data);
            LOGGER.debug("Image saved for id {}", image.getId());

            return new UploadResponse(image.getId(), true);
        } catch (Exception e) {
            LOGGER.error("Error in upload", e);
            return new UploadResponse(null, true);
        }
    }

}
