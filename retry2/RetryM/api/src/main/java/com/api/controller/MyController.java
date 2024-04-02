package com.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @GetMapping("/example/api")
    public String getInfo(){
        return "Info send";
    }

}
