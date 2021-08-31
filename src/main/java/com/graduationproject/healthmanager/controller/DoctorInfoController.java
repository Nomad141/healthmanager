package com.graduationproject.healthmanager.controller;

import com.graduationproject.healthmanager.mapper.DoctorInfoMapper;
import com.graduationproject.healthmanager.model.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;


import javax.print.Doc;
import java.util.List;
import java.util.Random;

@RestController
@CrossOrigin
public class DoctorInfoController {

    @Autowired
    DoctorInfoMapper doctorInfoMapper;

    @RequestMapping("/doctorinfo")
    public InitDoctor hasInfo(){
        System.out.println("收到医生信息页面初始化请求。");
        List<Doctor> a=doctorInfoMapper.findDoctorByUserName(GlobalData.getNowUserName());
        String tip="";
        Random random=new Random();
        try {
            List<String> tips = TxtUtils.getTxt("./samples/healthtips.txt");
            tip=tips.get(random.nextInt(tips.size())).toString();
            System.out.println(tip);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(a==null||a.isEmpty()){
            System.out.println("没有查询到医生信息。返回99");
            return new InitDoctor(99,tip);
        }else{
            System.out.println("已经存在医生信息。返回1");
            return new InitDoctor(1,tip);
        }
    }

    @PostMapping("/submitdoctorinfo")
    public Result submitInfo(@RequestBody Doctor doctor){
        System.out.println("开始录入医生信息。");
        System.out.println("收到医生信息:"+doctor.getDoctorName()+"/"+doctor.getTreatTime()+"/"+doctor.getDepartment());
        doctor.setTreatUser(GlobalData.getNowUserName());
        try {
            doctorInfoMapper.addDoctor(doctor);
            System.out.println("医生信息成功添加。");
            return new Result(999,"录入成功！");
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new Result(000,e.getMessage());
        }
    }

    @PostMapping("/deletedoctorinfo")
    public Result deleteDoctorInfo(@RequestBody Doctor doctor){
        System.out.println("收到医生记录删除请求。");
        System.out.println("要删除的记录："+doctor.getDoctorName()+"//"+doctor.getTreatTime()+"//"+doctor.getDepartment());
        doctor.setTreatUser(GlobalData.getNowUserName());
        try {
            doctorInfoMapper.deleteOne(doctor);
            System.out.println("成功删除一条记录。");
            return new Result(999,"删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(000,e.getMessage());
        }
    }
}
