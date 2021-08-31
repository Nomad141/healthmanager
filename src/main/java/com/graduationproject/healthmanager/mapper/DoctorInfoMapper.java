package com.graduationproject.healthmanager.mapper;

import com.graduationproject.healthmanager.model.Doctor;
import org.apache.ibatis.annotations.*;

import java.util.List;


public interface DoctorInfoMapper {

    @Select("SELECT * FROM DOCTOR WHERE treatuser=#{userName}")
    List<Doctor> findDoctorByUserName(String userName);

    @Insert("INSERT INTO DOCTOR (DOCTORNAME,TREATUSER,TREATTIME,DEPARTMENT) VALUES(#{doctor.doctorName},#{doctor.treatUser},#{doctor.treatTime},#{doctor.department})")
    void addDoctor(@Param("doctor")Doctor doctor);

    @Delete("DELETE FROM DOCTOR WHERE TREATUSER=#{username}")
    void deleteByUserName(@Param("username")String username);

    @Delete("DELETE FROM DOCTOR WHERE DOCTORNAME=#{doctor.doctorName} AND TREATUSER=#{doctor.treatUser} AND TREATTIME=#{doctor.treatTime} AND DEPARTMENT=#{doctor.department}")
    void deleteOne(@Param("doctor")Doctor doctor);
}
