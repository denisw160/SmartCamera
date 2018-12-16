package me.wirries.smartcamera.cameraservice.repository;

import me.wirries.smartcamera.cameraservice.model.ImageDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * This repository interacts with the model class {@link ImageDetail}.
 *
 * @author denisw
 * @version 1.0
 * @since 2018-12-16
 */
public interface ImageDetailRepository extends JpaRepository<ImageDetail, String> {

    /**
     * Find all details for the given id.
     *
     * @param idImage id of the image
     * @return List with details
     */
    List<ImageDetail> findAllByIdImageOrderByMeta(String idImage);

}
