package com.app.camvil.dto.requestdto;

public class BoardDeleteRequestDTO {
    private long userId;
    private long boardId;
    BoardDeleteRequestDTO(long userId, long boardId) {
        this.userId = userId;
        this.boardId = boardId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public long getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }
}
