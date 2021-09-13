package com.graduationproject.healthmanager.controller;

import com.graduationproject.healthmanager.mapper.HealthInfoMapper;
import com.graduationproject.healthmanager.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;


@RestController
@CrossOrigin
public class HealthInfoController {

    @Autowired
    HealthInfoMapper healthInfoMapper;

    //录入健康史
    @PostMapping("/submithistoryinfo")
    public Result submitHistoryInfo(@RequestBody HealthHistory healthHistory){
        System.out.println("开始录入健康史信息。");
        System.out.println("收到健康史信息："+healthHistory.getCheckTime()+"/"+healthHistory.getCheckResult()+"/"+healthHistory.getHasTreat());
        healthHistory.setUserName(GlobalData.getNowUserName());
        try {
            healthInfoMapper.addHealthHistory(healthHistory);
            System.out.println("一条健康史成功添加。");
            return new Result(999,"录入成功！");
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new Result(000,e.getMessage());
        }
    }

    //删除一条健康史信息
    @PostMapping("/deletehistory")
    public Result deleteHistory(@RequestBody HealthHistory history){
        System.out.println("收到删除健康史请求。");
        System.out.println("要删除的记录："+history.getCheckTime()+"//"+history.getCheckResult());
        history.setUserName(GlobalData.getNowUserName());
        try {
            healthInfoMapper.deleteOneHistory(history);
            System.out.println("一条健康史删除成功。");
            return new Result(999,"删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(000,e.getMessage());
        }
    }

    //录入一条医疗记录
    @PostMapping("/submittreatinfo")
    public Result submitTreatInfo(@RequestBody TreatHistory treatHistory){
        System.out.println("开始录入医疗记录信息。");
        System.out.println("收到医疗记录："+treatHistory.getTreatTime()+"/"+treatHistory.getTreatDisease()+"/"+treatHistory.getTreatDoctor()+"/"+treatHistory.getHasCure());
        treatHistory.setUserName(GlobalData.getNowUserName());
        try {
            healthInfoMapper.addTreatHistory(treatHistory);
            System.out.println("一条医疗记录成功添加。");
            return new Result(999,"录入成功！");
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new Result(000,e.getMessage());
        }
    }

    //删除一条医疗记录
    @PostMapping("/deletetreat")
    public Result deleteTreat(@RequestBody TreatHistory treatHistory){
        System.out.println("收到删除诊疗史请求。");
        System.out.println("要删除的记录："+treatHistory.getTreatDoctor()+"//"+treatHistory.getTreatTime()+"//"+treatHistory.getTreatDisease());
        treatHistory.setUserName(GlobalData.getNowUserName());
        try {
            healthInfoMapper.deleteOneTreat(treatHistory);
            System.out.println("一条诊疗记录删除成功。");
            return new Result(999,"删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(000,e.getMessage());
        }
    }

    //录入一条保健计划
    @PostMapping("/submitplaninfo")
    public Result submitPlanInfo(@RequestBody Plan plan){
        System.out.println("开始录入保健计划信息。");
        System.out.println("收到保健计划信息："+plan.getPlanTime()+"/"+(plan.getPlanDate().equals("")?"无":plan.getPlanDate())+"/"+plan.getPlanProject());
        plan.setUserName(GlobalData.getNowUserName());
        try {
            healthInfoMapper.addPlan(plan);
            System.out.println("一条保健计划成功添加。");
            return new Result(999,"录入成功！");
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new Result(000,e.getMessage());
        }
    }

    //删除一条保健计划
    @PostMapping("/deleteplan")
    public Result deletePlan(@RequestBody Plan plan){
        System.out.println("收到删除保健计划请求。");
        System.out.println("要删除的记录："+plan.getPlanProject());
        plan.setUserName(GlobalData.getNowUserName());
        try {
            healthInfoMapper.deleteOnePlan(plan);
            System.out.println("一条保健计划删除成功。");
            return new Result(999,"删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(000,e.getMessage());
        }
    }

    //初始化整个健康页面，如果数据库中已经有内容要返回显示。
    @RequestMapping("/inithealth")
    public InitHealth initHealth(){
        System.out.println("收到健康情况记录页面初始化请求。");
        List<HealthHistory> history=healthInfoMapper.selectHealthHistoryByUserName(GlobalData.getNowUserName());
        List<TreatHistory> treat=healthInfoMapper.selectTreatHistoryByUserName(GlobalData.getNowUserName());
        List<Plan> plan=healthInfoMapper.selectPlansByUserName(GlobalData.getNowUserName());
        String tip="";
        Random random=new Random();
        try {
            List<String> tips =TxtUtils.getTxt("./samples/healthtips.txt");
            tip=tips.get(random.nextInt(tips.size())).toString();
            System.out.println(tip);
        }catch (Exception e){
            e.printStackTrace();
        }
        InitHealth result=new InitHealth(1,1,1,tip);
        if (history==null||history.isEmpty()) {
            System.out.println("没有健康史记录！");
            result.setFlag1(0);
        }
        if (treat==null||treat.isEmpty()) {
            System.out.println("没有诊疗记录！");
            result.setFlag2(0);
        }
        if (plan==null||plan.isEmpty()) {
            System.out.println("没有保健计划记录！");
            result.setFlag3(0);
        }
        return result;
    }
}
