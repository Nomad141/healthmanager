package com.graduationproject.healthmanager.controller;

import com.graduationproject.healthmanager.mapper.AuthUserMapper;
import com.graduationproject.healthmanager.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@CrossOrigin
public class AuthController {

    @Autowired
    AuthUserMapper authUserMapper;

    //处理登录后的结果
    @PostMapping("/auth")
    public LoginResult auth(@RequestBody User user) {
        System.out.println("username:"+ user.getUsername());
        System.out.println("password:"+ user.getPassword());
        if(user.getUsername().equals("")||user.getPassword().equals("")){ //输入不合法（空）
            return new LoginResult(101,"xxBlankxx","输入的账号/密码不能为空！");
        }
        User nowUser=authUserMapper.findUserByName(user.getUsername());
        if(nowUser==null){ //用户不存在，自动注册
            authUserMapper.addUser(user);
            return new LoginResult(102,"registered","用户已成功注册，请登陆！");
        }
        if(!user.getPassword().equals(nowUser.getPassword())){ //密码不正确
            return new LoginResult(109,"wrongpassword","密码不正确！");
        }
        GlobalData.setNowUserName(user.getUsername());
        GlobalData.setNowUserPasswd(user.getPassword());
        return new LoginResult(100, user.getUsername()+"/"+user.getPassword(),"success!");
    }

    //处理修改密码请求
    @PostMapping("/changepaswd")
    public Result changePaswd(@RequestBody Password password){
        System.out.println("收到密码修改请求。");
        System.out.println("当前用户："+GlobalData.getNowUserName());
        try {
            User nowUser=authUserMapper.findUserByName(GlobalData.getNowUserName());
            if(password.getOldPaswd().equals(nowUser.getPassword())){
                authUserMapper.updatePaswd(GlobalData.getNowUserName(),password.getNewPaswd());
                System.out.println("密码修改完成。");
                return new Result(999,"success");
            }else {
                return new Result(111,"旧密码不正确！");
            }
        }catch (Exception e){
            e.printStackTrace();
            return new Result(000,e.getMessage());
        }
    }

    //初始化页面
    @RequestMapping("/home")
    public ResultWithTips homeName(){
        System.out.println("收到了home请求。返回用户信息给前端。");
        String tip="";
        Random random=new Random();
        try {
            List<String> tips =TxtUtils.getTxt("./samples/healthtips.txt");
            tip=tips.get(random.nextInt(tips.size())).toString();
            System.out.println(tip);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResultWithTips(111,GlobalData.getNowUserName(),tip);
    }

    @RequestMapping("/test")
    public String test(){
        List<User> x = authUserMapper.getAllUser();
        for (User y:x){
            System.out.println(y.getUsername()+"/"+y.getPassword());
        }
        return "ok";
    }


}
