package com.app.camvil.dto.responsedto;

public class CampsiteCountResponseDTO {
    private String campsiteCode;
    private long boardCnt;

    public CampsiteCountResponseDTO() {

    }

    public String getCampsiteCode() {
        return campsiteCode;
    }

    public void setCampsiteCode(String campsiteCode) {
        this.campsiteCode = campsiteCode;
    }

    public long getBoardCnt() {
        return boardCnt;
    }

    public void setBoardCnt(long boardCnt) {
        this.boardCnt = boardCnt;
    }
}
