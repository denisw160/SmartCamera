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

    /**
     * Find all images with images data and is not processed.
     *
     * @return List with not processed images
     */
    @Query("select i from Image as i join ImageData as d on (i.id = d.idImage) where i.processed = false order by i.timestamp asc")
    List<Image> findNotProcessed();

}
