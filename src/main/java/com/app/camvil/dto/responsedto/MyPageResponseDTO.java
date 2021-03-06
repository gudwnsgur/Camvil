package com.app.camvil.dto.responsedto;

import com.app.camvil.dto.UserDTO;

public class MyPageResponseDTO {
    private long userId;
    private String userEmail;
    private String userName;
    private String userImagePath;
    private String joinDate;

    public MyPageResponseDTO(UserDTO user) {
        this.userId = user.getUserId();
        this.userEmail = user.getUserEmail();
        this.userName = user.getUserName();
        this.userImagePath = user.getUserImagePath();
        this.joinDate = user.getJoinDate();
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
