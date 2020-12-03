package com.app.camvil.dto.responsedto;

public class CampsiteCountResponseDTO {
    private String campsiteCode;
    private int boardCnt;

    public CampsiteCountResponseDTO() {

    }

    public String getCampsiteCode() {
        return campsiteCode;
    }

    public void setCampsiteCode(String campsiteCode) {
        this.campsiteCode = campsiteCode;
    }

    public int getBoardCnt() {
        return boardCnt;
    }

    public void setBoardCnt(int boardCnt) {
        this.boardCnt = boardCnt;
    }
}
