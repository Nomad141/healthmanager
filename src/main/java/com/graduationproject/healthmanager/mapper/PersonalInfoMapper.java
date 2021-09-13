package com.graduationproject.healthmanager.mapper;

import com.graduationproject.healthmanager.model.User;
import org.apache.ibatis.annotations.*;


//登记的个人信息要用到的数据库接口
public interface PersonalInfoMapper {

    @Select("SELECT HASINFO FROM USERS WHERE USERNAME = #{name}")
    int hasInfoByName(String name);

    @Update("UPDATE USERS SET TRUENAME=#{user.trueName},BIRTHDAY=to_date(#{user.birthDay},'yyyy-mm-dd'),ANAPHYLACTIC=#{user.anaphylactic},SEX=#{user.sex},MEDICALHISTORY=#{user.medicalHistory},BLOODTYPE=#{user.bloodType},HASINFO=1 WHERE USERNAME=#{name}")
    void updateUserInfo(User user, @Param("name")String name);

}
