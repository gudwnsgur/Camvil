package com.app.camvil.dto.requestdto;

public class CommentDeleteRequestDTO {
    private int commentId;
    private int userId;

    public CommentDeleteRequestDTO( int commentId, int userId) {
        this.commentId = commentId;
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

}