package me.wirries.smartcamera.cameraservice.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.wirries.smartcamera.cameraservice.model.ImageData;
import me.wirries.smartcamera.cameraservice.model.ImageDetail;
import me.wirries.smartcamera.cameraservice.model.ImageProcessed;
import me.wirries.smartcamera.cameraservice.model.MetaTag;
import me.wirries.smartcamera.cameraservice.repository.ImageDataRepository;
import me.wirries.smartcamera.cameraservice.repository.ImageDetailRepository;
import me.wirries.smartcamera.cameraservice.repository.ImageProcessedRepository;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Scanning the image for barcodes.
 *
 * @author denisw
 * @version 1.0
 * @since 19.12.18
 */
@Component
public class BarcodeProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(BarcodeProcessor.class);

    private static final String SCRIPT_NAME = "barcode-processor.py";

    private ImageDetailRepository detailRepository;
    private ImageDataRepository dataRepository;
    private ImageProcessedRepository processedRepository;

    private boolean scriptDetected;

    @Autowired
    public BarcodeProcessor(ImageDetailRepository detailRepository,
                            ImageDataRepository dataRepository,
                            ImageProcessedRepository processedRepository) {
        this.detailRepository = detailRepository;
        this.dataRepository = dataRepository;
        this.processedRepository = processedRepository;

        File script = new File("./" + SCRIPT_NAME);
        scriptDetected = script.isFile();
        LOGGER.info("BarcodeProcessor found script: {}", scriptDetected);
    }

    public void process(String idImage) {
        LOGGER.debug("Loading data image for id {}", idImage);
        ObjectMapper mapper = new ObjectMapper();
        Optional<ImageData> row = dataRepository.findById(idImage);

        if (row.isPresent()) {
            ImageData data = row.get();

            File sourceImage = new File("./" + data.getIdImage() + "." + data.getExtension());
            File resultImage = new File("./" + data.getIdImage() + "-result." + data.getExtension());
            File resultTags = new File("./" + data.getIdImage() + ".json");

            try {
                // Store the image in working dir
                FileUtils.writeByteArrayToFile(sourceImage, data.getImage());

                // Execute processor
                if (scriptDetected) {
                    // Execute python script
                    LOGGER.debug("Execute command");
                    Process exec = Runtime.getRuntime().exec(new String[]{
                            "/usr/bin/python",
                            "barcode-processor.py",
                            "-i",
                            sourceImage.getAbsolutePath(),
                            "-o",
                            resultImage.getAbsolutePath(),
                            "-r",
                            resultTags.getAbsolutePath()
                    });

                    BufferedReader stdIn = new BufferedReader(new InputStreamReader(exec.getInputStream()));
                    String line;
                    while ((line = stdIn.readLine()) != null) {
                        LOGGER.debug(line);
                    }
                    stdIn.close();

                    int exitValue = exec.waitFor();
                    if (exitValue != 0)
                        LOGGER.error("Failed to execute processor, exitValue: {}", exitValue);

                } else {
                    // Dummy implementation
                    FileUtils.copyFile(sourceImage, resultImage);

                    // Create dummy data
                    List<MetaTag> tags = new ArrayList<>();
                    tags.add(new MetaTag("t1", "d1"));
                    tags.add(new MetaTag("t2", "d2"));
                    tags.add(new MetaTag("t2", "d3"));
                    mapper.writeValue(resultTags, tags);
                }

                // Read the results and store in database
                ImageProcessed result = new ImageProcessed();
                result.setIdImage(data.getIdImage());
                result.setMediaType(data.getMediaType());
                result.setExtension(data.getExtension());
                result.setImage(FileUtils.readFileToByteArray(resultImage));
                processedRepository.save(result);

                MetaTag[] tags = mapper.readValue(resultTags, MetaTag[].class);
                for (MetaTag t : tags) {
                    ImageDetail detail = new ImageDetail();
                    detail.setIdImage(data.getIdImage());
                    detail.setMeta(t.getType());
                    detail.setValue(t.getData());
                    detailRepository.save(detail);
                }

            } catch (Exception e) {
                LOGGER.error("Error while processing id " + idImage, e);

            } finally {
                // Cleanup
                FileUtils.deleteQuietly(sourceImage);
                FileUtils.deleteQuietly(resultImage);
                FileUtils.deleteQuietly(resultTags);
            }
        }
    }

}
