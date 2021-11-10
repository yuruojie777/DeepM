package com.ele5620.deepm;

import com.ele5620.deepm.dao.UserMapper;
import com.ele5620.deepm.entity.User;
import com.ele5620.deepm.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = DeepMApplication.class)
public class UserMapperTests {
    @Autowired
    private UserMapper userMapper;
//    @Test
//    public void testInsert(){
//        User user = new User();
//        user.setName("yuruojie");
//        user.setGender(1);
//        user.setEmail("yuruojie111@qq.com");
//        user.setSalt("123");
//        user.setStatus(1);
//        user.setAvatar("123");
//        user.setRole(1);
//        user.setCreateTime(new Timestamp(System.currentTimeMillis()));
//        user.setPassword(CommonUtil.md5("123" + user.getSalt()));
//        userMapper.insertUser(user);
//    }

    @Test
    public void testSelect() {
        User user = userMapper.selectByEmail("123@qq.com");
        System.out.println(user);
    }
}
