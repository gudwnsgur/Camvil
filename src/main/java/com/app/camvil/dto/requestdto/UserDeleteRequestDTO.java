package com.app.camvil.dto.requestdto;

public class UserDeleteRequestDTO {
    private long userId;

    UserDeleteRequestDTO (long userId) {
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }
}
