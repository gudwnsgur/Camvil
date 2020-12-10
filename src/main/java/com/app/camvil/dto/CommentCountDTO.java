package com.app.camvil.dto;

public class CommentCountDTO {
    private long boardId;
    private long commentCnt;

    public CommentCountDTO() {}

    public long getBoardId() {
        return boardId;
    }

    public long getCommentCnt() {
        return commentCnt;
    }
}
