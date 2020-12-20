package com.app.camvil.dto;

import org.apache.ibatis.type.Alias;

@Alias("UserDto")
public class UserDTO {
    private long userId;
    private String userSid;
    private String userEmail;
    private String userName;
    private String userImagePath;
    private String fcmToken;
    private String joinDate;
    private boolean userAuth;
    private boolean externalImage;

    public UserDTO() {}
    public UserDTO(String userSid, String userEmail,
                   String userName, String userImagePath, String fcmToken) {
        this.userSid = userSid;
        this.userEmail=userEmail;
        this.userName=userName;
        this.userImagePath = userImagePath;
        this.fcmToken = fcmToken;
    }
    public UserDTO(String userSid, String userEmail,
                   String userName,  String fcmToken) {
        this.userSid = userSid;
        this.userEmail=userEmail;
        this.userName=userName;
        this.fcmToken = fcmToken;
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

    public String getFcmToken() {
        return fcmToken;
    }

    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }

    public boolean isUserAuth() {
        return userAuth;
    }

    public void setUserAuth(boolean userAuth) {
        this.userAuth = userAuth;
    }


    public long getUserId() {
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

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public boolean isExternalImage() {
        return externalImage;
    }

    public void setExternalImage(boolean externalImage) {
        this.externalImage = externalImage;
    }
}
