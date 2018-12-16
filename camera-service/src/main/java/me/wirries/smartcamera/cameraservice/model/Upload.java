package me.wirries.smartcamera.cameraservice.model;

/**
 * This class represents the upload of the file.
 *
 * @author denisw
 * @version 1.0
 * @since 2018-12-16
 */
public class Upload {

    private String name;

    public Upload() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Upload{" +
                "name='" + name + '\'' +
                '}';
    }

}
