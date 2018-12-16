package me.wirries.smartcamera.cameraservice.repository;

import me.wirries.smartcamera.cameraservice.model.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This repository interacts with the model class {@link ImageData}.
 *
 * @author denisw
 * @version 1.0
 * @since 2018-12-16
 */
public interface ImageDataRepository extends JpaRepository<ImageData, String> {

}
