package me.wirries.smartcamera.cameraservice.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This class represents the details of an image.
 *
 * @author denisw
 * @version 1.0
 * @since 2018-12-16
 */
@Entity
@Table(name = "META")
@GenericGenerator(name = "uuid", strategy = "uuid2")
public class ImageDetail {

    @Id
    @GeneratedValue(generator = "uuid")
    private String id;
    private String idImage;
    private String meta;
    private String value;

    public ImageDetail() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdImage() {
        return idImage;
    }

    public void setIdImage(String idImage) {
        this.idImage = idImage;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ImageDetail that = (ImageDetail) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "ImageDetail{" +
                "id='" + id + '\'' +
                ", idImage='" + idImage + '\'' +
                ", meta='" + meta + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

}
