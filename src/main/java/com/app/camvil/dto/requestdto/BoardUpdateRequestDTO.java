package com.app.camvil.dto.requestdto;


public class BoardUpdateRequestDTO {
    private int userId;
    private int boardId;
    private String campsiteCode;
    private String campsiteName;
    private double mapX;
    private double mapY;
    private String boardContent;

    public BoardUpdateRequestDTO(int userId, int boardId, String campsiteCode, String campsiteName,
                                 double mapX, double mapY, String boardContent) {
        this.userId = userId;
        this.boardId = boardId;
        this.campsiteCode = campsiteCode;
        this.campsiteName = campsiteName;
        this.mapX = mapX;
        this.mapY = mapY;
        this.boardContent = boardContent;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
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
}
