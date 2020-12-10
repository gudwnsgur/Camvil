package com.app.camvil.dto;

import org.apache.ibatis.type.Alias;

@Alias("SearchDto")
public class SearchDTO {
    private String searchContent;
    private long searchCnt;

    public SearchDTO() {

    }
    public String getSearchContent() {
        return searchContent;
    }

    public void setSearchContent(String searchContent) {
        this.searchContent = searchContent;
    }

    public long getSearchCnt() {
        return searchCnt;
    }

    public void setSearchCnt(long searchCnt) {
        this.searchCnt = searchCnt;
    }
}

