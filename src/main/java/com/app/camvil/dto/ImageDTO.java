package com.app.camvil.dto;

import org.apache.ibatis.type.Alias;

@Alias("ImageDto")
public class ImageDTO {
    private int imageId;
    private int boardId;
    private String imageName;
    private String imagePath;

    public ImageDTO(){

    }
    public ImageDTO(int board_id, String imageName, String imagePath) {
        this.boardId = board_id;
        this.imageName = imageName;
        this.imagePath = imagePath;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
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