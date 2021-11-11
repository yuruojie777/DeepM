package com.ele5620.deepm.controller;
import com.ele5620.deepm.entity.User;
import com.ele5620.deepm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String,Object> map, HttpSession session, HttpServletResponse response){
        Map result = new HashMap<String, Object>();
        if(map.get("email") == null) {
            result.put("status", "email can not be null");
            return result;
        }
        if(map.get("password") == null) {
            result.put("status", "password can not be null");
            return result;
        }
        String email = (String)map.get("email");
        String password = (String)map.get("password");
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        System.out.println(email);
        System.out.println(password);
        result = userService.login(user);

        //if exists, then add to cookie
        if(result.containsKey("ticket")) {
            Cookie cookie = new Cookie("ticket", result.get("ticket").toString());
            Cookie cookie1 = new Cookie("role", result.get("role").toString());
            Cookie cookie2 = new Cookie("id", result.get("id").toString());
            cookie.setPath("/deepm");
            cookie.setMaxAge(3600 * 12);
            response.addCookie(cookie);
            response.addCookie(cookie1);
            response.addCookie(cookie2);
        }
        return result;
    }

    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody Map<String,Object> map) {
        Map<String, Object> result = new HashMap<String, Object>();
        System.out.println(map);
        if(map.get("email") == null) {
            result.put("status", "email can not be null");
            return result;
        }
        if(map.get("name") == null) {
            result.put("status", "name can not be null");
            return result;
        }
        if(map.get("password") == null) {
            result.put("status", "password can not be null");
            return result;
        }
        if(map.get("gender") == null) {
            result.put("status", "gender can not be null");
            return result;
        }
        String email = (String)map.get("email");
        String name = (String)map.get("name");
        String password = (String) map.get("password");
//        int gender = Integer.parseInt((String) map.get("gender"));
        int gender = map.get("gender").equals("male")?1:0;
        int role = (int)map.get("role");
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(password);
        user.setGender(gender);
        user.setRole(role);
        result = userService.register(user);

        return result;
    }
}
