package com.app.camvil.dto;

import org.apache.ibatis.type.Alias;

@Alias("LikeDto")
public class LikeDTO {
    private long userId;
    private long boardId;
    private boolean like_;

    public LikeDTO() {}
    public LikeDTO(long userId, long boardId) {
        this.userId = userId;
        this.boardId = boardId;
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

    public boolean isLike_() {
        return like_;
    }

    public void setLike_(boolean like_) {
        this.like_ = like_;
    }
}

