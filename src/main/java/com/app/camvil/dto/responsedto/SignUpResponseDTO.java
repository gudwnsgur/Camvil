package com.app.camvil.dto.responsedto;

import com.app.camvil.dto.UserDTO;

public class SignUpResponseDTO {
    private long userId;
    private String userSid;
    private String userEmail;
    private String userName;
    private String userImagePath;
    private String fcmToken;
    private String joinDate;

    public SignUpResponseDTO(UserDTO user) {
        this.userId = user.getUserId();
        this.userSid = user.getUserSid();
        this.userEmail = user.getUserEmail();
        this.userName = user.getUserName();
        this.userImagePath = user.getUserImagePath();
        this.fcmToken = user.getFcmToken();
        this.joinDate = user.getJoinDate();
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setUserSid(String userSid) {
        this.userSid = userSid;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }

    public void setUserImagePath(String userImagePath) {
        this.userImagePath = userImagePath;
    }
}
