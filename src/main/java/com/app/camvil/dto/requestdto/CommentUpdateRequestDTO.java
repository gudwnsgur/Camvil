package com.app.camvil.dto.requestdto;

public class CommentUpdateRequestDTO {
    private long commentId;
    private long userId;
    private String commentContent;

    public CommentUpdateRequestDTO(long commentId, long userId, String commentContent) {
        this.commentId = commentId;
        this.userId = userId;
        this.commentContent = commentContent;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }


    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }
}

