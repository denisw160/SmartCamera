package me.wirries.smartcamera.cameraservice.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * This class represents the processed file.
 *
 * @author denisw
 * @version 1.0
 * @since 2018-12-19
 */
@Entity
@Table(name = "PROCESSED")
public class ImageProcessed {

    @Id
    private String idImage;
    private String mediaType;
    private String extension;
    @Lob
    private byte[] image;

    public ImageProcessed() {
    }

    public String getIdImage() {
        return idImage;
    }

    public void setIdImage(String idImage) {
        this.idImage = idImage;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ImageProcessed imageProcessed = (ImageProcessed) o;

        return new EqualsBuilder()
                .append(idImage, imageProcessed.idImage)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(idImage)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "ImageProcessed{" +
                "idImage='" + idImage + '\'' +
                ", mediaType='" + mediaType + '\'' +
                ", extension='" + extension + '\'' +
                '}';
    }

}
