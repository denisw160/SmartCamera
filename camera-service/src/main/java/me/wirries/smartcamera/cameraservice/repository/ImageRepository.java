package me.wirries.smartcamera.cameraservice.repository;

import me.wirries.smartcamera.cameraservice.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * This repository interacts with the model class {@link me.wirries.smartcamera.cameraservice.model.Image}.
 *
 * @author denisw
 * @version 1.0
 * @since 2018-12-16
 */
public interface ImageRepository extends JpaRepository<Image, String> {

    /**
     * Return all images in order of their timestamp.
     *
     * @return List of images
     */
    @Override
    @Query("from Image order by timestamp desc")
    List<Image> findAll();

}
