package com.app.camvil.dto.requestdto;

public class UserPagingDTO {
    private long userId;
    private int pageNumber;
    private int pageSize;

    public UserPagingDTO() {}
    public UserPagingDTO(long userId, int pageNumber, int pageSize) {
        this.userId = userId;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber() {
        if(this.pageNumber == 0 ) this.pageNumber = 1;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
