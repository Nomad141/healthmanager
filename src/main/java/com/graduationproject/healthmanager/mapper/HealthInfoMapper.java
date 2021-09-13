package com.graduationproject.healthmanager.mapper;

import com.graduationproject.healthmanager.model.HealthHistory;
import com.graduationproject.healthmanager.model.Plan;
import com.graduationproject.healthmanager.model.TreatHistory;
import org.apache.ibatis.annotations.*;
import org.omg.CORBA.portable.ValueOutputStream;

import java.util.List;

//健康信息要用到的数据库接口
public interface HealthInfoMapper {

    //HealthHistory
    @Select("SELECT * FROM HISTORY WHERE USERNAME=#{username}")
    List<HealthHistory> selectHealthHistoryByUserName(@Param("username")String username);

    @Insert("INSERT INTO HISTORY (USERNAME,CHECKTIME,CHECKRESULT,HASTREAT) VALUES(#{history.userName},to_date(#{history.checkTime},'yyyy-mm-dd'),#{history.checkResult},#{history.hasTreat})")
    void addHealthHistory(@Param("history")HealthHistory history);

    @Delete("DELETE FROM HISTORY WHERE USERNAME=#{username}")
    void deleteHistoryByUserName(@Param("username")String username);

    @Delete("DELETE FROM HISTORY WHERE USERNAME=#{history.userName} AND CHECKTIME=to_date(substr(#{history.checkTime},1,10),'yyyy-mm-dd') AND CHECKRESULT=#{history.checkResult}")
    void deleteOneHistory(@Param("history")HealthHistory history);

    //TreatHistory
    @Select("SELECT * FROM TREAT WHERE USERNAME=#{username}")
    List<TreatHistory> selectTreatHistoryByUserName(@Param("username")String username);

    @Insert("INSERT INTO TREAT (USERNAME,TREATTIME,TREATDOCTOR,HASCURE,TREATDISEASE) VALUES(#{history.userName},to_date(#{history.treatTime},'yyyy-mm-dd'),#{history.treatDoctor},#{history.hasCure},#{history.treatDisease})")
    void addTreatHistory(@Param("history")TreatHistory history);

    @Delete("DELETE FROM TREAT WHERE USERNAME=#{username}")
    void deleteTreatByUserName(@Param("username")String username);

    @Delete("DELETE FROM TREAT WHERE USERNAME=#{history.userName} AND TREATTIME=to_date(substr(#{history.treatTime},1,10),'yyyy-mm-dd') AND TREATDOCTOR=#{history.treatDoctor} AND TREATDISEASE=#{history.treatDisease}")
    void deleteOneTreat(@Param("history")TreatHistory history);

    //Plans
    @Select("SELECT * FROM PLANS WHERE USERNAME=#{username}")
    List<Plan> selectPlansByUserName(@Param("username")String username);

    @Insert("INSERT INTO PLANS (USERNAME,PLANTIME,PLANDATE,PLANPROJECT) VALUES(#{plan.userName},#{plan.planTime},to_date(#{plan.planDate},'yyyy-mm-dd'),#{plan.planProject})")
    void addPlan(@Param("plan")Plan plan);

    @Delete("DELETE FROM PLANS WHERE USERNAME=#{username}")
    void deletePlanByUserName(@Param("username")String username);

    @Delete("DELETE FROM PLANS WHERE USERNAME=#{plan.userName} AND PLANPROJECT=#{plan.planProject}")
    void deleteOnePlan(@Param("plan")Plan plan);
}
