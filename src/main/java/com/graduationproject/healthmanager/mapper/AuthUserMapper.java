package com.graduationproject.healthmanager.mapper;

import com.graduationproject.healthmanager.model.User;
import org.apache.ibatis.annotations.*;


import java.util.List;

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
