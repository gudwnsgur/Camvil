package com.app.camvil.dto.requestdto;

public class BoardsRequestDTO {
    private String order;   // null or "like_count"
    private String campsiteCode; // null or "[CAMPSITE CODE]"
    private String search;
    private int pageNumber;
    private int pageSize;

    public BoardsRequestDTO(String order, String campsiteCode, String search,
                            int pageNumber, int pageSize) {
        this.order = order;
        this.campsiteCode = campsiteCode;
        this.search = search;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }
    public void setPageNumber() {
        if(this.pageNumber == 0 ) this.pageNumber = 1;
    }
    public String getOrder() {
        return order;
    }

    public String getCampsiteCode() {
        return campsiteCode;
    }

    public String getSearch() {
        return search;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }
}
