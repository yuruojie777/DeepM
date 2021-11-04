package com.ele5620.deepm.dao;

import com.ele5620.deepm.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Timestamp;

@Mapper
public interface UserMapper {

    User selectById(int id);
    User selectByEmail(String email);

    User selectByName(String name);

    int insertUser(int id, int role, String name, String email, String password, String avatar, String salt, int gender, Timestamp createTime, int status);

    int updateUserName(int id, String name);
    int updateUserPassword(int id, String password);
    int updateUserAvatar(int id, String avatar);
    int updateUserStatus(int id, int status);
}
