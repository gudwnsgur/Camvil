package com.app.camvil.dto.requestdto;

public class UserIdRequestDTO {
    private long userId;

    UserIdRequestDTO(long userId) {
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }
}
