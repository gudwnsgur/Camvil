package com.app.camvil.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {
    @RequestMapping(value = "/", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
    public String index() {
        return "index";
    }
}
