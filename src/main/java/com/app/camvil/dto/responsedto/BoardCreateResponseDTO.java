package com.app.camvil.dto.responsedto;

import com.app.camvil.dto.ImageListDTO;

import java.util.ArrayList;
import java.util.List;

public class BoardCreateResponseDTO {
    private long boardId;
    private long userId;
    private String userName;
    private String userImagePath;
    private String boardContent;
    private String campsiteCode;
    private List<ImageListDTO> images;

    public BoardCreateResponseDTO(long boardId, long userId, String userName, String userImagePath,
            String boardContent, String campsiteCode, List<ImageListDTO> images) {
        this.boardId = boardId;
        this.userId = userId;
        this.userName = userName;
        this.userImagePath = userImagePath;
        this.boardContent = boardContent;
        this.campsiteCode = campsiteCode;
        this.images = images;
    }

    public void setBoardContent(String boardContent) {
        this.boardContent = boardContent;
    }

    public void setCampsiteCode(String campsiteCode) {
        this.campsiteCode = campsiteCode;
    }


    public void setBoardId(long boardId) {
        this.boardId = boardId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setImages(List<ImageListDTO> images) {
        this.images = images;
    }
}
