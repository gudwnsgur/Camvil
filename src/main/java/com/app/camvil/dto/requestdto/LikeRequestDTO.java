package com.app.camvil.dto.requestdto;

public class LikeRequestDTO {
    private long userId;
    private long boardId;
    private String like_;

    public LikeRequestDTO(long userId, long boardId, String like_) {
        this.userId = userId;
        this.boardId = boardId;
        this.like_ = like_;
    }

    public long getUserId() {
        return userId;
    }

    public long getBoardId() {
        return boardId;
    }

    public String getLike_() {
        return like_;
    }
}

