package com.app.camvil.repository;

import com.app.camvil.dto.CampsiteDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CampsiteRepository {
    CampsiteDTO findCampsiteNameByCode(String campsiteCode);
    void insertCampsite(CampsiteDTO campsite);
}

