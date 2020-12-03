package com.app.camvil.dto.responsedto;

public class CommentDetailResponseDTO {
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
}
