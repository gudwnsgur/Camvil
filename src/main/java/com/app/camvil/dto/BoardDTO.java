package com.app.camvil.dto;

import org.apache.ibatis.type.Alias;

@Alias("BoardDto")
public class BoardDTO {
    private int boardId;
    private int userId;
    private String campsiteCode;
    private String boardContent;
    private int commentCnt;
    private int likeCnt;
    private String postDate;

    public BoardDTO(int userId, String campsiteCode,
                    String boardContent) {
        this.userId = userId;
        this.campsiteCode = campsiteCode;
        this.boardContent = boardContent;
    }

    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }


    public String getCampsiteCode() {
        return campsiteCode;
    }

    public void setCampsiteCode(String campsiteCode) {
        this.campsiteCode = campsiteCode;
    }

    public String getBoardContent() {
        return boardContent;
    }

    public void setBoardContent(String boardContent) {
        this.boardContent = boardContent;
    }

    public int getCommentCnt() {
        return commentCnt;
    }

    public void setCommentCnt(int commentCnt) {
        this.commentCnt = commentCnt;
    }

    public int getLikeCnt() {
        return likeCnt;
    }

    public void setLikeCnt(int likeCnt) {
        this.likeCnt = likeCnt;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }
}
