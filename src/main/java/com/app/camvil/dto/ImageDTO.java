package com.app.camvil.dto;

import org.apache.ibatis.type.Alias;

@Alias("ImageDto")
public class ImageDTO {
    private long imageId;
    private long boardId;
    private String imageName;
    private String imagePath;

    public ImageDTO(){

    }
    public ImageDTO(long board_id, String imageName, String imagePath) {
        this.boardId = board_id;
        this.imageName = imageName;
        this.imagePath = imagePath;
    }

    public long getImageId() {
        return imageId;
    }

    public void setImageId(long imageId) {
        this.imageId = imageId;
    }

    public long getBoardId() {
        return boardId;
    }

    public void setBoardId(long boardId) {
        this.boardId = boardId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}