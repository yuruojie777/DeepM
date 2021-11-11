package com.ele5620.deepm.controller;

import com.ele5620.deepm.entity.User;
import com.ele5620.deepm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public Map<String, Object> viewUser(@RequestParam int id) {
        Map<String, Object> map = new HashMap<String, Object>();
        User user = userService.findUserById(id);
        if(user != null){
            map.put("data", userService.findUserById(id));
            map.put("status", "success");
            return map;
        }else{
            map.put("status","user doesn't exist");
            return map;
        }
    }

    @DeleteMapping
    public Map<String, Object> deleteUser(@RequestParam int id) {
        Map<String, Object> map = new HashMap<>();
        User user = userService.findUserById(id);
        if(user != null){
            userService.deleteUser(id);
            map.put("status", "success");
            return map;
        }
        map.put("status", "the user doesn't exist");

        return map;
    }
}
