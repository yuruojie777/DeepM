package com.ele5620.deepm.service;

import com.ele5620.deepm.dao.UserMapper;
import com.ele5620.deepm.entity.User;
import com.ele5620.deepm.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    private String domain;

    private String contextPath;

    public User findUserById(int uid) {
        return userMapper.selectById(uid);
    }


    //register, for this part
    //
    public Map<String, Object> register(User user) {
        Map<String, Object> map = new HashMap<>();

        //if input is null
        if(user == null) {
            throw new IllegalArgumentException("The parameter can not be null");
        }


        //verify name
        User  u = userMapper.selectByName(user.getName());
        if(u != null){
            map.put("userNameMsg", "Username already exist");
            System.out.println("Username already exist");
            return map;
        }

        //verify email
        u = userMapper.selectByEmail(user.getEmail());
        if(u != null){
            map.put("emailMsg", "Email already exist");
            return map;
        }

        //register
        user.setSalt(CommonUtil.generateUUID().substring(0,5));

        user.setPassword(CommonUtil.md5(user.getPassword() + user.getSalt()));
        user.setCreateTime(new Timestamp(System.currentTimeMillis()));
        user.setRole(1);
        user.setStatus(0);
        user.setAvatar("http:123.56.59.240:8080/avatar/c611b87517514bcc81497699f894985d.png");


//        userMapper.insertUser(forumUser);
//        int uid = userMapper.selectByEmail(forumUser.getEmail()).getUid();
//        //activation email
//        Context context = new Context();
//        context.setVariable("email", forumUser.getEmail());
//        //http://localhost:8080/community/activation/101/code
//        String url = domain + contextPath+"/activation/" + uid +  '/' + forumUser.getActivationCode();
//        context.setVariable("url",url);
//        System.out.println(url);
//        String content = templateEngine.process("mail/activation", context);
//        mailClient.sendMail(forumUser.getEmail(), "Activate account", content);

        return map;
    }


    public Map<String, Object> login(String email, String password, int type) {
        User user = userMapper.selectByEmail(email);

        return null;
    }

}
