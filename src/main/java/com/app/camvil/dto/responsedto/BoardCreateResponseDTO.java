package com.app.camvil.dto.responsedto;

import java.util.ArrayList;

public class BoardCreateResponseDTO {
    private String boardContent;
    private String campsiteCode;
    private ArrayList<String> imagePath;

    public BoardCreateResponseDTO(String boardContent, String campsiteCode, ArrayList<String> imagePath) {
        this.boardContent = boardContent;
        this.campsiteCode = campsiteCode;
        this.imagePath = imagePath;
    }

    public void setBoardContent(String boardContent) {
        this.boardContent = boardContent;
    }

    public void setCampsiteCode(String campsiteCode) {
        this.campsiteCode = campsiteCode;
    }

    public void setImagePath(ArrayList<String> imagePath) {
        this.imagePath = imagePath;
    }
}
