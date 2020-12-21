package com.app.camvil.dto;

public class FcmTokenDTO {
    private long userId;
    private String fcmToken;

    public FcmTokenDTO() {}
    public FcmTokenDTO(long userId, String fcmToken) {
        this.userId = userId;
        this.fcmToken = fcmToken;
    }
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getFcmToken() {
        return fcmToken;
    }

    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }
}
