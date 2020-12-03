package com.app.camvil.dto.requestdto;

public class BoardDeleteRequestDTO {
    private int userId;
    private int boardId;
    BoardDeleteRequestDTO(int userId, int boardId) {
        this.userId = userId;
        this.boardId = boardId;
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
}
