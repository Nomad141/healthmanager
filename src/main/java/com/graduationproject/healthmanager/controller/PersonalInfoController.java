package com.graduationproject.healthmanager.controller;

import com.graduationproject.healthmanager.mapper.AuthUserMapper;
import com.graduationproject.healthmanager.mapper.HabitsMapper;
import com.graduationproject.healthmanager.mapper.PersonalInfoMapper;
import com.graduationproject.healthmanager.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;


@RestController
@CrossOrigin
public class PersonalInfoController {

    @Autowired
    PersonalInfoMapper personalInfoMapper;
    @Autowired
    HabitsMapper habitsMapper;
    @Autowired
    AuthUserMapper authUserMapper;

    //初始化个人信息页面
    @RequestMapping("/personalinfo")
    public InitPersonal hasInfo(){
        System.out.println("收到个人信息页面初始化请求。返回信息是否已经登记。");
        InitPersonal initPersonal=new InitPersonal();
        initPersonal.setFlag1(personalInfoMapper.hasInfoByName(GlobalData.getNowUserName()));
        if(habitsMapper.selectHabitByUserName(GlobalData.getNowUserName())==null){
            initPersonal.setFlag2(0);
        }else {
            initPersonal.setFlag2(1);
        }
        String tip="";
        Random random=new Random();
        try {
            List<String> tips =TxtUtils.getTxt("./samples/healthtips.txt");
            tip=tips.get(random.nextInt(tips.size())).toString();
            System.out.println(tip);
            initPersonal.setTip(tip);
        }catch (Exception e){
            e.printStackTrace();
        }
        return initPersonal;
    }

    @RequestMapping("/initpersonalinfo")
    public User initInfo(){
        System.out.println("尝试返回个人信息...");
        if(personalInfoMapper.hasInfoByName(GlobalData.getNowUserName())==1){
            System.out.println("有个人信息。返回。");
            return authUserMapper.findUserByName(GlobalData.getNowUserName());
        }else {
            System.out.println("这个用户还没有记录个人信息。");
            return null;
        }
    }

    @RequestMapping("/inithabit")
    public Habits initHabit(){
        System.out.println("尝试返回生活习惯信息...");
        if (habitsMapper.selectHabitByUserName(GlobalData.getNowUserName())!=null){
            System.out.println("有生活习惯信息。返回。");
            return habitsMapper.selectHabitByUserName(GlobalData.getNowUserName());
        }else {
            System.out.println("这个用户还没有记录习惯信息。");
            return null;
        }
    }

    @PostMapping("/submitinfo")
    public Result submitInfo(@RequestBody User user){
        System.out.println("开始更新个人信息。");
        System.out.println("收到的个人信息："+user.getTrueName()+"/"+user.getBirthDay()+"/"+user.getSex()+"/"+user.getAnaphylactic()+"/"+user.getMedicalHistory()+"/"+user.getBloodType());
        try {
            personalInfoMapper.updateUserInfo(user,GlobalData.getNowUserName());
            System.out.println("信息成功录入。");
            return new Result(999,"更新成功！");
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new Result(000,"录入失败：请检查您的信息填写是否完整！");
        }
    }

    @PostMapping("/submithabit")
    public Result submitHabit(@RequestBody Habits habit){
        System.out.println("开始录入/更新习惯信息。");
        System.out.println("收到个人习惯信息："+habit.getSleepTime()+"/"+habit.getWeakUpTime()+"/"+habit.getMeals()+"/"+habit.getPressure());
        habit.setUserName(GlobalData.getNowUserName());
        if (habitsMapper.selectHabitByUserName(GlobalData.getNowUserName())==null){
            System.out.println("录入新的习惯信息。");
            try {
                habitsMapper.insertHabits(habit);
                System.out.println("信息录入成功。");
                return new Result(999,"录入成功！");
            }catch (Exception e){
                e.printStackTrace();
                return new Result(000,"录入失败：请检查您的信息填写是否完整！");
            }
        }else {
            System.out.println("更新习惯信息。");
            try {
                habitsMapper.updateHabit(GlobalData.getNowUserName(),habit);
                System.out.println("信息录入成功。");
                return new Result(999,"更新成功！");
            }catch (Exception e){
                e.printStackTrace();
                return new Result(000,"更新失败：请检查您的信息填写是否完整！");
            }
        }
    }
}
