package com.ele5620.deepm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class HomeController {
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String getHomePage(){
        return "/index";
    }
}
