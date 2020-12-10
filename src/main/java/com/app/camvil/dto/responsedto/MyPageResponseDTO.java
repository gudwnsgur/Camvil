package com.app.camvil.dto.responsedto;

public class MyPageResponseDTO {
    private long userId;
    private String userEmail;
    private String userName;
    private String userImagePath;
    private String joinDate;

    public MyPageResponseDTO(long userId, String userEmail, String userName,
                             String userImagePath, String joinDate) {
        this.userId = userId;
        this.userEmail = userEmail;
        this.userName = userName;
        this.userImagePath = userImagePath;
        this.joinDate = joinDate;
    }
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserImagePath() {
        return userImagePath;
    }

    public void setUserImagePath(String userImagePath) {
        this.userImagePath = userImagePath;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
