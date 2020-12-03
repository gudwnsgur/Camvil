package com.app.camvil.dto.requestdto;

public class CommentCreateRequestDTO {
    private int userId;
    private int boardId;
    private String commentContent;

    public CommentCreateRequestDTO(int userId, int boardId, String commentContent) {
        this.userId = userId;
        this.boardId = boardId;
        this.commentContent = commentContent;
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

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }
}
