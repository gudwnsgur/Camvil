package com.app.camvil.dto.requestdto;

public class CommentCreateRequestDTO {
    private long userId;
    private long boardId;
    private String commentContent;

    public CommentCreateRequestDTO(long userId, long boardId, String commentContent) {
        this.userId = userId;
        this.boardId = boardId;
        this.commentContent = commentContent;
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

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }
}
