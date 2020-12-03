package com.app.camvil.dto;

import org.apache.ibatis.type.Alias;

@Alias("LikeDto")
public class LikeDTO {
    private int userId;
    private int boardId;
    private boolean like_;

    public LikeDTO() {}
    public LikeDTO(int userId, int boardId) {
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

    public boolean isLike_() {
        return like_;
    }

    public void setLike_(boolean like_) {
        this.like_ = like_;
    }
}

