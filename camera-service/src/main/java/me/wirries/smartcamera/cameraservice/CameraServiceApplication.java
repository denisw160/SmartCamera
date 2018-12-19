package me.wirries.smartcamera.cameraservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * This is the main class of the application and starts the service.
 *
 * @author denisw
 * @version 1.0
 * @since 16.12.2018
 */
@EnableScheduling
@SpringBootApplication
public class CameraServiceApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(CameraServiceApplication.class);

    public static void main(String[] args) {
        LOGGER.info("Starting the CameraService ...");
        SpringApplication.run(CameraServiceApplication.class, args);
    }

}

