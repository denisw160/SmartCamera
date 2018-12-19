package me.wirries.smartcamera.cameraservice.model;

/**
 * This is a meta tag from the processor.
 * <p>
 * {"type": "CODE128", "data": "86441"}
 *
 * @author denisw
 * @version 1.0
 * @since 19.12.18
 */
public class MetaTag {

    private String type;
    private String data;

    public MetaTag() {
    }

    public MetaTag(String type, String data) {
        this.type = type;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "MetaTag{" +
                "type='" + type + '\'' +
                ", data='" + data + '\'' +
                '}';
    }

}
