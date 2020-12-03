package com.app.camvil.dto.requestdto;

public class CommentUpdateRequestDTO {
    private int commentId;
    private int userId;
    private String commentContent;

    public CommentUpdateRequestDTO(int commentId, int userId, String commentContent) {
        this.commentId = commentId;
        this.userId = userId;
        this.commentContent = commentContent;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }
}

