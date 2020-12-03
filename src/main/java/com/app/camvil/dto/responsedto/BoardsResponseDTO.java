package com.app.camvil.dto.responsedto;

import java.util.ArrayList;

public class BoardsResponseDTO {

    private String userName;
    private String boardContent;
    private String campsiteName;
    private String postDate;
    private int commentCnt;
    private int likeCnt;
    private ArrayList<String> imagePath;

    public BoardsResponseDTO(String userName, String boardContent, String campsiteName,
                             String postDate, int commentCnt, int likeCnt, ArrayList<String> imagePath) {
        this.userName = userName;
        this.boardContent = boardContent;
        this.campsiteName = campsiteName;
        this.postDate = postDate;
        this.commentCnt = commentCnt;
        this.likeCnt = likeCnt;
        this.imagePath = imagePath;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }


    public void setBoardContent(String boardContent) {
        this.boardContent = boardContent;
    }


    public void setCampsiteName(String campsiteName) {
        this.campsiteName = campsiteName;
    }


    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }


    public void setCommentCnt(int commentCnt) {
        this.commentCnt = commentCnt;
    }

    public void setLikeCnt(int likeCnt) {
        this.likeCnt = likeCnt;
    }

    public ArrayList<String> getImagePath() {
        return imagePath;
    }

    public void setImagePath(ArrayList<String> imagePath) {
        this.imagePath = imagePath;
    }
}
