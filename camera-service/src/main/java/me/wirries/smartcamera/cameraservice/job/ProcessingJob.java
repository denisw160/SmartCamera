package me.wirries.smartcamera.cameraservice.job;

import me.wirries.smartcamera.cameraservice.model.Image;
import me.wirries.smartcamera.cameraservice.processor.BarcodeProcessor;
import me.wirries.smartcamera.cameraservice.processor.TextProcessor;
import me.wirries.smartcamera.cameraservice.repository.ImageDetailRepository;
import me.wirries.smartcamera.cameraservice.repository.ImageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * This is the job for processing the images. The job only runs with spring
 * profile production.
 *
 * @author denisw
 * @version 1.0
 * @since 19.12.18
 */
@Component
@Profile("production")
public class ProcessingJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessingJob.class);

    private ImageRepository imageRepository;
    private ImageDetailRepository detailRepository;

    private BarcodeProcessor barcodeProcessor;
    private TextProcessor textProcessor;

    private boolean running = false;

    @Autowired
    public ProcessingJob(ImageRepository imageRepository,
                         ImageDetailRepository detailRepository,
                         BarcodeProcessor barcodeProcessor,
                         TextProcessor textProcessor) {

        LOGGER.info("Setting up the processing job");
        this.imageRepository = imageRepository;
        this.detailRepository = detailRepository;
        this.barcodeProcessor = barcodeProcessor;
        this.textProcessor = textProcessor;
    }

    @Scheduled(fixedRate = 5000)
    public void start() {
        if (!running) {
            LOGGER.debug("Running job");
            running = true;

            List<Image> notProcessed = imageRepository.findNotProcessed();
            for (Image i : notProcessed) {
                LOGGER.debug("Processing: {}", i);

                // Processing
                barcodeProcessor.process(i.getId());
                textProcessor.process(i.getId());

                // Updating tag count
                int count = detailRepository.countByIdImage(i.getId());
                i.setTags(count);
                i.setProcessed(true);
                imageRepository.save(i);
                LOGGER.debug("Image processing completed");
            }

            running = false;
        }
    }

}
