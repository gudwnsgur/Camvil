package com.app.camvil.dto;

import org.apache.ibatis.type.Alias;

@Alias("BoardDto")
public class BoardDTO {
    private long boardId;
    private long userId;
    private String campsiteCode;
    private String boardContent;
    private long commentCnt;
    private long likeCnt;
    private String postDate;

    public BoardDTO(long userId, String campsiteCode,
                    String boardContent) {
        this.userId = userId;
        this.campsiteCode = campsiteCode;
        this.boardContent = boardContent;
    }

    public long getBoardId() {
        return boardId;
    }

    public void setBoardId(long boardId) {
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

    public long getCommentCnt() {
        return commentCnt;
    }

    public void setCommentCnt(long commentCnt) {
        this.commentCnt = commentCnt;
    }

    public long getLikeCnt() {
        return likeCnt;
    }

    public void setLikeCnt(long likeCnt) {
        this.likeCnt = likeCnt;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }
}
