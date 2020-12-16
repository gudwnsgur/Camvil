package com.app.camvil.dto.requestdto;

public class UserUpdateRequestDTO {
    private long userId;
    private String userName;
    private String userImage;
    private String fcmToken;

    public long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserImage() {
        return userImage;
    }

    public String getFcmToken() {
        return fcmToken;
    }
}

