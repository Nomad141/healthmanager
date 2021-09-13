package com.graduationproject.healthmanager.mapper;

import com.graduationproject.healthmanager.model.Habits;
import org.apache.ibatis.annotations.*;

//个人习惯信息所要用到的数据库接口
public interface HabitsMapper {

    @Insert("INSERT INTO HABITS(USERNAME,SLEEPTIME,WEAKUPTIME,MEALS,PRESSURE) VALUES(#{habit.userName},#{habit.sleepTime},#{habit.weakUpTime},#{habit.meals},#{habit.pressure})")
    void insertHabits(@Param("habit")Habits habit);

    @Select("SELECT * FROM HABITS WHERE USERNAME = #{username}")
    Habits selectHabitByUserName(@Param("username")String username);

    @Update("UPDATE HABITS SET SLEEPTIME=#{habit.sleepTime},WEAKUPTIME=#{habit.weakUpTime},MEALS=#{habit.meals},PRESSURE=#{habit.pressure} WHERE USERNAME=#{username}")
    void updateHabit(@Param("username")String username,@Param("habit")Habits habit);

    @Delete("DELETE FROM HABITS WHERE USERNAME=#{username}")
    void deleteByUserName(@Param("username")String username);
}
