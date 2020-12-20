package com.app.camvil.dto;

import org.apache.ibatis.type.Alias;

@Alias("CommentDto")
public class CommentDTO {
    private long commentId;
    private String commentContent;
    private long userId;
    private long boardId;
    private String postDate;
    private String updateDate;

    public CommentDTO() {}
    public CommentDTO(String commentContent, long userId, long boardId) {
        this.commentContent = commentContent;
        this.userId = userId;
        this.boardId = boardId;
    }
    public CommentDTO(long commentId, String commentContent) {
        this.commentId = commentId;
        this.commentContent = commentContent;
    }
    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
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

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }
}
