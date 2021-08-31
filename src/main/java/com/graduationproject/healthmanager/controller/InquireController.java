package com.graduationproject.healthmanager.controller;


import com.graduationproject.healthmanager.mapper.*;
import com.graduationproject.healthmanager.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.resource.AbstractResourceResolver;

import javax.print.Doc;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin
public class InquireController {

    @Autowired
    PersonalInfoMapper personalInfoMapper;
    @Autowired
    AuthUserMapper authUserMapper;
    @Autowired
    DoctorInfoMapper doctorInfoMapper;
    @Autowired
    HealthInfoMapper healthInfoMapper;
    @Autowired
    HabitsMapper habitsMapper;

    @RequestMapping("/findpersonalinfo")
    public Result findPersonalInfo(){
        System.out.println("开始返回个人信息。");
        System.out.println("当前用户："+ GlobalData.getNowUserName());
        try {
            User nowUser=authUserMapper.findUserByName(GlobalData.getNowUserName());
            Habits habits=habitsMapper.selectHabitByUserName(GlobalData.getNowUserName());
            String bloodType="";
            switch (nowUser.getBloodType()){
                case 1:
                    bloodType="A";
                    break;
                case 2:
                    bloodType="B";
                    break;
                case 3:
                    bloodType="AB";
                    break;
                case 4:
                    bloodType="O";
                    break;
                default:
                    bloodType="其他";
            }
            String meals="";
            String pressure="";
            switch (habits.getMeals()){
                case 1:
                    meals="规律";
                    break;
                case 2:
                    meals="基本规律";
                    break;
                case 3:
                    meals="不规律";
                    break;
            }
            switch (habits.getPressure()){
                case 1:
                    pressure="压力很小";
                    break;
                case 2:
                    pressure="有部分压力";
                    break;
                case 3:
                    pressure="压力大";
                    break;
            }
            String result="真实姓名："+nowUser.getTrueName()+"\n<br>性别："+(nowUser.getSex()>0?"男":"女")+"\n<br>生日："+nowUser.getBirthDay()+"\n<br>药物过敏史："+
                    nowUser.getAnaphylactic()+"\n<br>疾病史："+nowUser.getMedicalHistory()+"\n<br>血型："+bloodType;
            System.out.println(result);
            result+="<br><br>您的作息时间："+habits.getSleepTime()+"--"+habits.getWeakUpTime()+"\n<br>您的饮食规律水平："+meals+"\n<br>您的压力水平："+pressure;
            return new Result(111,result);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new Result(999,e.getMessage());
        }
    }

    @RequestMapping("/finddoctorinfo")
    @ResponseBody
    public List<Doctor> findDoctorInfo(){
        System.out.println("开始返回医生信息。");
        System.out.println("当前用户："+ GlobalData.getNowUserName());
        try {
            List<Doctor> doctors = doctorInfoMapper.findDoctorByUserName(GlobalData.getNowUserName());
            String result="";
            for(Doctor doctor:doctors){
                result+="医生名："+doctor.getDoctorName()+"\n诊疗时间："+doctor.getTreatTime()+"\n负责疾病："+doctor.getDepartment()+"\n\n";
            }
            System.out.println(result);
            return doctors;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @RequestMapping("/findhistoryinfo")
    public List<HealthHistory> findHistoryInfo(){
        System.out.println("开始返回健康史信息。");
        System.out.println("当前用户："+ GlobalData.getNowUserName());
        try {
            List<HealthHistory> healthHistories=healthInfoMapper.selectHealthHistoryByUserName(GlobalData.getNowUserName());
            String result="";
            for (HealthHistory healthHistory:healthHistories){
                result+="检查时间："+healthHistory.getCheckTime()+"\n检查结果："+healthHistory.getCheckResult()+"\n已经治愈："
                        +(healthHistory.getHasTreat()==1?"是":"否")+"\n\n";
            }
            System.out.println(result);
            return healthHistories;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @RequestMapping("/findtreatinfo")
    public List<TreatHistory> findTreatInfo(){
        System.out.println("开始返回医疗记录信息。");
        System.out.println("当前用户："+ GlobalData.getNowUserName());
        try {
            List<TreatHistory> treatHistories=healthInfoMapper.selectTreatHistoryByUserName(GlobalData.getNowUserName());
            String result="";
            for (TreatHistory treatHistory:treatHistories){
                result+="诊疗时间："+treatHistory.getTreatTime()+"\n病症："+treatHistory.getTreatDisease()+"\n主治医师："+treatHistory.getTreatDoctor()+
                        "\n已经治愈："+(treatHistory.getHasCure()==1?"是":"否")+"\n\n";
            }
            System.out.println(result);
            return treatHistories;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @RequestMapping("/findplan")
    public List<Plan> findPlan(){
        System.out.println("开始返回保健计划信息。");
        System.out.println("当前用户："+ GlobalData.getNowUserName());
        try {
            List<Plan> plans=healthInfoMapper.selectPlansByUserName(GlobalData.getNowUserName());
            String result="";
            for (Plan plan:plans){
                String planTime="";
                switch (plan.getPlanTime()){
                    case 1:
                        planTime="每天";
                        break;
                    case 2:
                        planTime="每周";
                        break;
                    case 3:
                        planTime="每月";
                        break;
                    case 4:
                        planTime="每年";
                        break;
                    case 5:
                        planTime="指定日期："+ plan.getPlanDate();
                        break;
                }
                result+="计划时间："+planTime+"\n<br>计划项目："+plan.getPlanProject()+"\n\n<br><br>";
            }
            System.out.println(result);
            return plans;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
