package com.ele5620.deepm.controller;
import com.ele5620.deepm.entity.User;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/user")
@RestController
public class LoginController {
    @PostMapping
    public Map<String, Object> addpersion(@RequestBody Map<String,Object> map){
        Map result = new HashMap<String, Object>();
        result.put("status", "ok");
        return map;
    }

    @GetMapping
    public User showUser() {
        User user = new User();
        user.setUid(1);
        user.setRole(1);
        user.setAvatar("aaa");
        user.setPassword("123");
        user.setCreateTime(new Timestamp(System.currentTimeMillis()));
        user.setStatus(1);
        user.setSalt("1");
        user.setEmail("ssfsfs");
        user.setGender(1);
        user.setName("yu");
        return user;
    }

    @PutMapping
    public void changeUser() {

    }
}
