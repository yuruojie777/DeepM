package com.ele5620.deepm.dao;
import com.ele5620.deepm.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface UserMapper {

    List<User> selectAllUser();
    User selectById(int id);
    User selectByEmail(String email);
    User selectByName(String name);
    List<User> selectByRole(int role);
    int insertUser(User user);
    int insertRelation(int sid, int tid);


    int updateUserName(int id, String name);
    int updateUserPassword(int id, String password);
    int updateUserAvatar(int id, String avatar);
    int updateUserStatus(int id, int status);

    int deleteUser(int id);
}
