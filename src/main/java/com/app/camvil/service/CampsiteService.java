package com.app.camvil.service;

import com.app.camvil.dto.CampsiteDTO;
import com.app.camvil.repository.CampsiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CampsiteService {
    @Autowired
    private CampsiteRepository repository;

    public void insertCampsite(CampsiteDTO campsite) {
        repository.insertCampsite(campsite);
    }
    public CampsiteDTO findCampsiteNameByCode(String campsiteName) {return repository.findCampsiteNameByCode(campsiteName);}

}