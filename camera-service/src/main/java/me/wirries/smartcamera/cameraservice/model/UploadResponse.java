package me.wirries.smartcamera.cameraservice.model;

/**
 * This class represents the response of an image upload.
 *
 * @author denisw
 * @version 1.0
 * @since 2018-12-16
 */
public class UploadResponse {

    private String id;
    private Boolean success;


    public UploadResponse() {
    }

    public UploadResponse(String id, Boolean success) {
        this.id = id;
        this.success = success;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "UploadResponse{" +
                "id='" + id + '\'' +
                ", success=" + success +
                '}';
    }

}
