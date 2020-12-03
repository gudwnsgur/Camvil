package com.app.camvil.dto.requestdto;

public class BoardsRequestDTO {
    private String order;
    private String filter;
    private String campsiteCode;

    public BoardsRequestDTO(String order, String filter, String campsiteCode) {
        this.order = order;
        this.filter = filter;
        this.campsiteCode = campsiteCode;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getCampsiteCode() {
        return campsiteCode;
    }

    public void setCampsiteCode(String campsiteCode) {
        this.campsiteCode = campsiteCode;
    }
}
