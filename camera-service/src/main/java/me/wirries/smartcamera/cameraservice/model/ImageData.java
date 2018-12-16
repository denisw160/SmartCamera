package me.wirries.smartcamera.cameraservice.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * This class represents the upload of the file.
 *
 * @author denisw
 * @version 1.0
 * @since 2018-12-16
 */
@Entity
@Table(name = "DATA")
public class ImageData {

    @Id
    private String idImage;
    private String mediaType;
    @Lob
    private byte[] image;

    public ImageData() {
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

        ImageData imageData = (ImageData) o;

        return new EqualsBuilder()
                .append(idImage, imageData.idImage)
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
        return "ImageData{" +
                "idImage='" + idImage + '\'' +
                ", mediaType='" + mediaType + '\'' +
                '}';
    }

}
