package com.app.camvil.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {
    @RequestMapping(value = "/test", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
    public String testIndex() {
        return "hello world";
    }
}
