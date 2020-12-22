package com.app.camvil.dto.responsedto;

import com.app.camvil.dto.BoardsDTO;
import com.app.camvil.dto.ImageListDTO;

import java.util.List;

public class BoardsResponseDTO {
    private long boardId;
    private long userId;
    private String userImagePath;
    private String userName;
    private String campsiteCode;
    private String campsiteName;
    private double mapX;
    private double mapY;
    private String boardContent;
    private List<ImageListDTO> images;
    private List<CommentDetailResponseDTO> comments;
    private String postDate;
    private String updateDate;

    private long commentCnt;
    private long likeCnt;

    public BoardsResponseDTO() {}

    public BoardsResponseDTO(BoardsDTO boardsDTO,
                             List<ImageListDTO> images,
                             List<CommentDetailResponseDTO> comments ) {
        this.boardId = boardsDTO.getBoardId();
        this.userId = boardsDTO.getUserId();
        this.userImagePath = boardsDTO.getUserImagePath();
        this.userName = boardsDTO.getUserName();
        this.campsiteCode = boardsDTO.getCampsiteCode();
        this.campsiteName = boardsDTO.getCampsiteName();
        this.mapX = boardsDTO.getMapX();
        this.mapY = boardsDTO.getMapY();
        this.boardContent = boardsDTO.getBoardContent();
        this.images = images;
        this.comments = comments;
        this.postDate = boardsDTO.getPostDate();
        this.updateDate = boardsDTO.getUpdateDate();
        this.commentCnt = boardsDTO.getCommentCnt();
        this.likeCnt = boardsDTO.getLikeCnt();
    }
    public long getBoardId() {
        return boardId;
    }

    public void setBoardId(long boardId) {
        this.boardId = boardId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserImagePath() {
        return userImagePath;
    }

    public void setUserImagePath(String userImagePath) {
        this.userImagePath = userImagePath;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCampsiteCode() {
        return campsiteCode;
    }

    public void setCampsiteCode(String campsiteCode) {
        this.campsiteCode = campsiteCode;
    }

    public String getCampsiteName() {
        return campsiteName;
    }

    public void setCampsiteName(String campsiteName) {
        this.campsiteName = campsiteName;
    }

    public double getMapX() {
        return mapX;
    }

    public void setMapX(double mapX) {
        this.mapX = mapX;
    }

    public double getMapY() {
        return mapY;
    }

    public void setMapY(double mapY) {
        this.mapY = mapY;
    }

    public String getBoardContent() {
        return boardContent;
    }

    public void setBoardContent(String boardContent) {
        this.boardContent = boardContent;
    }

    public List<ImageListDTO> getImages() {
        return images;
    }

    public void setImages(List<ImageListDTO> images) {
        this.images = images;
    }

    public List<CommentDetailResponseDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDetailResponseDTO> comments) {
        this.comments = comments;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
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
}
