package com.app.camvil.dto.requestdto;

public class LikeRequestDTO {
    private int userId;
    private int boardId;
    private String like_;

    public LikeRequestDTO(int userId, int boardId, String like_) {
        this.userId = userId;
        this.boardId = boardId;
        this.like_ = like_;
    }

    public int getUserId() {
        return userId;
    }

    public int getBoardId() {
        return boardId;
    }

    public String getLike_() {
        return like_;
    }
}

