package com.app.camvil.dto.responsedto;

public class CommentResponseDTO {
    private long userId;
    private long boardId;
    private long commentId;

    public CommentResponseDTO (long userId, long boardId, long commentId) {
        this.userId = userId;
        this.boardId = boardId;
        this.commentId = commentId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setBoardId(long boardId) {
        this.boardId = boardId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }
}
