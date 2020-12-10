package com.app.camvil.dto.requestdto;

public class CommentDeleteRequestDTO {
    private long commentId;
    private long userId;

    public CommentDeleteRequestDTO( long commentId, long userId) {
        this.commentId = commentId;
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

}