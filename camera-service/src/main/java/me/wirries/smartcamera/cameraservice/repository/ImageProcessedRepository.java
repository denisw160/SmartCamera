package me.wirries.smartcamera.cameraservice.repository;

import me.wirries.smartcamera.cameraservice.model.ImageProcessed;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This repository interacts with the model class {@link ImageProcessed}.
 *
 * @author denisw
 * @version 1.0
 * @since 2018-12-19
 */
public interface ImageProcessedRepository extends JpaRepository<ImageProcessed, String> {

}
