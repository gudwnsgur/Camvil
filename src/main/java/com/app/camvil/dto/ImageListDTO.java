package com.app.camvil.dto;


public class ImageListDTO {
    private long imageId;
    private String imagePath;

    public ImageListDTO(long imageId, String imagePath){
        this.imageId = imageId;
        this.imagePath = imagePath;
    }

    public long getImageId() {
        return imageId;
    }

    public void setImageId(long imageId) {
        this.imageId = imageId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}