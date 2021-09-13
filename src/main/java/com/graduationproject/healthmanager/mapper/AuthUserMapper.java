package com.graduationproject.healthmanager.mapper;

import com.graduationproject.healthmanager.model.User;
import org.apache.ibatis.annotations.*;


import java.util.List;

//用户的注册登录验证等功能所要用到的数据库接口
public interface AuthUserMapper {
    @Select("SELECT * FROM USERS")
    List<User> getAllUser();

    @Insert("INSERT INTO USERS(USERNAME,PASSWORD) VALUES(#{username},#{password})")
    void addUser(User user);

    @Select("SELECT * FROM USERS WHERE USERNAME = #{username}")
    User findUserByName(String username);

    @Delete("DELETE FROM USERS WHERE USERNAME=#{username}")
    void deleteByUserName(@Param("username")String username);

    @Update("UPDATE USERS SET PASSWORD=#{newpasswd} WHERE USERNAME=#{username}")
    void updatePaswd(@Param("username")String username,@Param("newpasswd")String newpasswd);
}
