package com.app.camvil.dto;

import org.apache.ibatis.type.Alias;

@Alias("CommentDto")
public class CommentDTO {
    private int commentId;
    private String commentContent;
    private int userId;
    private int boardId;
    private String postDate;

    public CommentDTO() {}
    public CommentDTO(String commentContent, int userId, int boardId) {
        this.commentContent = commentContent;
        this.userId = userId;
        this.boardId = boardId;
    }
    public CommentDTO(int commentId, String commentContent) {
        this.commentId = commentId;
        this.commentContent = commentContent;
    }
    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
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

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }
}
