package com.ele5620.deepm.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/user")
@RestController
public class UserController {
    @PostMapping
    public Map<String, Object> addUser(@RequestBody Map<String, Object> map) {
        return null;
    }
}
