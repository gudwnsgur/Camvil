package com.app.camvil.dto.requestdto;


import java.util.ArrayList;

public class BoardCreateRequestDTO {
    private int userId;
    private String campsiteCode;
    private String campsiteName;
    private double mapX;
    private double mapY;
    private String boardContent;
    private ArrayList<String> images;

    public BoardCreateRequestDTO(int userId, String campsiteCode, String campsiteName,
                                 double mapX, double mapY, String boardContent,
                                 ArrayList<String> images) {
        this.userId = userId;
        this.campsiteCode = campsiteCode;
        this.campsiteName = campsiteName;
        this.mapX = mapX;
        this.mapY = mapY;
        this.boardContent = boardContent;
        this.images = images;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCampsiteCode() {
        return campsiteCode;
    }

    public void setCampsiteCode(String campsiteCode) {
        this.campsiteCode = campsiteCode;
    }

    public String getCampsiteName() {
        return campsiteName;
    }

    public void setCampsiteName(String campsiteName) {
        this.campsiteName = campsiteName;
    }

    public double getMapX() {
        return mapX;
    }

    public void setMapX(double mapX) {
        this.mapX = mapX;
    }

    public double getMapY() {
        return mapY;
    }

    public void setMapY(double mapY) {
        this.mapY = mapY;
    }

    public String getBoardContent() {
        return boardContent;
    }

    public void setBoardContent(String boardContent) {
        this.boardContent = boardContent;
    }

    public ArrayList<String> getImages() {
        return images;
    }
    public void setImages(ArrayList<String> images) {
        this.images = images;
    }
}