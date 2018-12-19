package me.wirries.smartcamera.cameraservice.processor;

import me.wirries.smartcamera.cameraservice.model.ImageData;
import me.wirries.smartcamera.cameraservice.model.ImageDetail;
import me.wirries.smartcamera.cameraservice.repository.ImageDataRepository;
import me.wirries.smartcamera.cameraservice.repository.ImageDetailRepository;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Optional;

/**
 * Scanning the image for texts.
 *
 * @author denisw
 * @version 1.0
 * @since 19.12.18
 */
@Component
public class TextProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(TextProcessor.class);

    private static final String SCRIPT_NAME = "text-processor.py";

    private ImageDetailRepository detailRepository;
    private ImageDataRepository dataRepository;

    private boolean scriptDetected;

    @Autowired
    public TextProcessor(ImageDetailRepository detailRepository,
                         ImageDataRepository dataRepository) {
        this.detailRepository = detailRepository;
        this.dataRepository = dataRepository;

        File script = new File("./" + SCRIPT_NAME);
        scriptDetected = script.isFile();
        LOGGER.info("TextProcessor found script: {}", scriptDetected);
    }

    public void process(String idImage) {
        LOGGER.debug("Loading data image for id {}", idImage);
        Optional<ImageData> row = dataRepository.findById(idImage);

        if (row.isPresent()) {
            ImageData data = row.get();

            File sourceImage = new File("./" + data.getIdImage() + "." + data.getExtension());
            File resultText = new File("./" + data.getIdImage() + ".txt");

            try {
                // Store the image in working dir
                FileUtils.writeByteArrayToFile(sourceImage, data.getImage());

                // Execute processor
                if (scriptDetected) {
                    // Execute python script
                    Process exec = Runtime.getRuntime().exec(new String[]{
                            "/usr/bin/python",
                            "text-processor.py",
                            "-i",
                            sourceImage.getAbsolutePath(),
                            "-r",
                            resultText.getAbsolutePath()
                    });

                    int exitValue = exec.waitFor();
                    if (exitValue != 0)
                        LOGGER.error("Failed to execute processor, exitValue: {}", exitValue);

                } else {
                    // Dummy implementation
                    PrintWriter out = new PrintWriter(resultText);
                    out.println("Sample text");
                    out.println("Sample line 2");
                    out.flush();
                    out.close();
                }

                // Read the results and store in database
                BufferedReader br = new BufferedReader(new FileReader(resultText));
                for (String line; (line = br.readLine()) != null; ) {
                    if (StringUtils.isBlank(line)) continue;

                    ImageDetail detail = new ImageDetail();
                    detail.setIdImage(data.getIdImage());
                    detail.setMeta("text");
                    detail.setValue(line);
                    detailRepository.save(detail);
                }
                br.close();

            } catch (Exception e) {
                LOGGER.error("Error while processing id " + idImage, e);

            } finally {
                // Cleanup
                FileUtils.deleteQuietly(sourceImage);
                FileUtils.deleteQuietly(resultText);
            }
        }
    }

}
