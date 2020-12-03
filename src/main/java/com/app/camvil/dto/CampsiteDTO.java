package com.app.camvil.dto;


import org.apache.ibatis.type.Alias;

@Alias("CampsiteDto")
public class CampsiteDTO {
    private String campsiteCode;
    private String campsiteName;
    private double mapX;
    private double mapY;

    public CampsiteDTO() {}
    public CampsiteDTO(String campsiteCode, String campsiteName,
                       double mapX, double mapY) {
        this.campsiteCode = campsiteCode;
        this.campsiteName = campsiteName;
        this.mapX = mapX;
        this.mapY = mapY;
    }

    public String getCampsiteCode() {
        return campsiteCode;
    }

    public void setCampsiteCode(String campsiteCode) {
        this.campsiteCode = campsiteCode;
    }

    public String getCampsiteName() {
        return campsiteName;
    }

    public void setCampsiteName(String campsiteName) {
        this.campsiteName = campsiteName;
    }

    public double getMapX() {
        return mapX;
    }

    public void setMapX(double mapX) {
        this.mapX = mapX;
    }

    public double getMapY() {
        return mapY;
    }

    public void setMapY(double mapY) {
        this.mapY = mapY;
    }
}
