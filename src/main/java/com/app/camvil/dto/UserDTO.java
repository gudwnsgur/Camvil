package com.app.camvil.dto;

import org.apache.ibatis.type.Alias;

@Alias("UserDto")
public class UserDTO {
    private int userId;
    private String userSid;
    private String userEmail;
    private String userName;
    private String userImagePath;
    private String accessToken;
    private String joinDate;
    private boolean userAuth;

    public UserDTO() {}
    public UserDTO(String userSid, String userEmail,
                   String userName, String userImagePath, String accessToken) {
        this.userSid = userSid;
        this.userEmail=userEmail;
        this.userName=userName;
        this.userImagePath = userImagePath;
        this.accessToken = accessToken;
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

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public boolean isUserAuth() {
        return userAuth;
    }

    public void setUserAuth(boolean userAuth) {
        this.userAuth = userAuth;
    }


    public int getUserId() {
        return userId;
    }

    public String getUserImagePath() {
        return userImagePath;
    }

    public void setUserImagePath(String userImagePath) {
        this.userImagePath = userImagePath;
    }

    public String getUserSid() {
        return userSid;
    }

    public void setUserSid(String userSid) {
        this.userSid = userSid;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }
}
