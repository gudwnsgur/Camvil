package com.app.camvil.dto.requestdto;

public class UserDeleteRequestDTO {
    private int userId;

    UserDeleteRequestDTO (int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }
}
