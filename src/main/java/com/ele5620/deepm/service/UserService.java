package com.ele5620.deepm.service;

import com.alibaba.fastjson.JSONObject;
import com.ele5620.deepm.controller.LoginController;
import com.ele5620.deepm.dao.LoginTicketMapper;
import com.ele5620.deepm.dao.UserMapper;
import com.ele5620.deepm.entity.LoginTicket;
import com.ele5620.deepm.entity.User;
import com.ele5620.deepm.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private LoginTicketMapper loginTicketMapper;
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    private String domain;
    private String contextPath;
    public User findUserById(int uid) {
        return userMapper.selectById(uid);
    }
    public User findUserByEmail(String email) { return userMapper.selectByEmail(email);}

    public Map<String, Object> register(User user) {
        Map<String, Object> map = new HashMap<>();

        //verify email
        User u = userMapper.selectByEmail(user.getEmail());
        if(u != null){
            map.put("status", "Email already exist");
            return map;
        }


        //register
        user.setSalt(CommonUtil.generateUUID().substring(0,5));
        user.setPassword(CommonUtil.md5(user.getPassword() + user.getSalt()));
        user.setCreateTime(new Timestamp(System.currentTimeMillis()));
        user.setStatus(0);
        user.setAvatar("http:123.56.59.240:8080/avatar/c611b87517514bcc81497699f894985d.png");
        userMapper.insertUser(user);
        map.put("status", "success");
        map.put("data", user);
        return map;
    }


    //login
    public Map<String, Object> login(User user) {

        Map<String, Object> result = new HashMap<>();
        User appuser = userMapper.selectByEmail(user.getEmail());
        if(appuser == null) {
            result.put("status", "user doesn't exist");
            System.out.println(result);
            return result;
        }
        if(!appuser.getPassword().equals(CommonUtil.md5(user.getPassword() + appuser.getSalt()))){
            result.put("status", "password is not correct");
            System.out.println(result);
            return result;
        }

        result.put("status", "success");
        result.put("role", appuser.getRole());
        result.put("id", appuser.getUid());
        System.out.println(result);

        //add loginTicket to table
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setUid(appuser.getUid());
        loginTicket.setTicket(CommonUtil.generateUUID());
        loginTicket.setStatus(0);
        loginTicket.setExpired(new Date(System.currentTimeMillis() + 3600 * 12 * 1000));
        loginTicketMapper.insertLoginTicket(loginTicket);

        result.put("ticket", loginTicket.getTicket());
        return result;
    }

    //change password
    public Map<String, Object> changePassword(int id, String pwd) {
        Map<String, Object> result = new HashMap<>();
        User user = userMapper.selectById(id);
        if(user == null){
            result.put("status", "user doesn't exist");
            return result;
        }
        if(pwd == null) {
            result.put("status", "password can not be null");
            return result;
        }
        user.setPassword(CommonUtil.md5(pwd + user.getSalt()));
        userMapper.updateUserPassword(id, pwd);
        result.put("status", "success");
        result.put("data", user);
        System.out.println(user.getRole());
        return result;
    }

    //change name
    public Map<String, Object> changeUserName(int id, String username) {
        Map<String, Object> result = new HashMap<>();
        User user = userMapper.selectById(id);
        if(user == null){
            result.put("status", "user doesn't exist");
            return result;
        }
        if(username == null) {
            result.put("status", "username can not be null");
            return result;
        }
        user.setPassword(username);
        userMapper.updateUserName(id, username);
        result.put("status", "success");
        result.put("data", user);
        return result;
    }


    //change avatar
    public Map<String, Object> changeAvatar(int id, String avatarURL) {
        Map<String, Object> result = new HashMap<>();
        User user = userMapper.selectById(id);
        if(user == null){
            result.put("status", "user doesn't exist");
            return result;
        }
        if(avatarURL == null) {
            result.put("status", "password can not be null");
            return result;
        }
        user.setAvatar(avatarURL);
        userMapper.updateUserAvatar(id, avatarURL);
        result.put("status", "success");
        result.put("data", user);
        return result;
    }


    //change status
    public Map<String, Object> changeStatus(int id, int status) {
        Map<String, Object> result = new HashMap<>();
        User user = userMapper.selectById(id);
        if(user == null){
            result.put("status", "user doesn't exist");
            return result;
        }
        user.setStatus(status);
        userMapper.updateUserStatus(id, status);
        result.put("status", "success");
        result.put("data", user);
        return result;
    }

    public LoginTicket findLoginTicket(String ticket) {
        return loginTicketMapper.selectByTicket(ticket);
    }

    public int deleteUser(int id){
        return userMapper.deleteUser(id);
    }

}
