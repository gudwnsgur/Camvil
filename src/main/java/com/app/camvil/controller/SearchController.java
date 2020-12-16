package com.app.camvil.controller;

import com.app.camvil.dto.SearchDTO;
import com.app.camvil.service.UserService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SearchController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/search", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public String search() {
        Gson gson = new GsonBuilder().create();
        Map<String, Object> response = new HashMap<>();

        List<SearchDTO> searchDTOList = userService.getSearch();

        response.put("responseCode", 200);
        response.put("responseMessage", "OK");
        response.put("responseBody", searchDTOList);
        return gson.toJson(response);
    }
}
