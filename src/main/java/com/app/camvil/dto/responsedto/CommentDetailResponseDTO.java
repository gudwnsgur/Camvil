package com.app.camvil.dto.responsedto;

public class CommentDetailResponseDTO {
    private long userId;
    private long commentId;
    private String userName;
    private String userImagePath;
    private String commentContent;
    private String postDate;

    public CommentDetailResponseDTO() {

    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserImagePath(String userImagePath) {
        this.userImagePath = userImagePath;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }
}
