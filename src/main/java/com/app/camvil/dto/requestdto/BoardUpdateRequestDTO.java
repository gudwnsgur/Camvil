package com.app.camvil.dto.requestdto;


public class BoardUpdateRequestDTO {
    private long userId;
    private long boardId;
    private String campsiteCode;
    private String campsiteName;
    private double mapX;
    private double mapY;
    private String boardContent;

    public BoardUpdateRequestDTO(long userId, long boardId, String campsiteCode, String campsiteName,
                                 double mapX, double mapY, String boardContent) {
        this.userId = userId;
        this.boardId = boardId;
        this.campsiteCode = campsiteCode;
        this.campsiteName = campsiteName;
        this.mapX = mapX;
        this.mapY = mapY;
        this.boardContent = boardContent;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getBoardId() {
        return boardId;
    }

    public void setBoardId(long boardId) {
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
