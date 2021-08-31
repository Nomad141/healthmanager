package com.graduationproject.healthmanager.controller;

import com.graduationproject.healthmanager.mapper.AuthUserMapper;
import com.graduationproject.healthmanager.mapper.DoctorInfoMapper;
import com.graduationproject.healthmanager.mapper.HealthInfoMapper;
import com.graduationproject.healthmanager.mapper.PersonalInfoMapper;
import com.graduationproject.healthmanager.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class PDFController {

    @Autowired
    PersonalInfoMapper personalInfoMapper;
    @Autowired
    AuthUserMapper authUserMapper;
    @Autowired
    DoctorInfoMapper doctorInfoMapper;
    @Autowired
    HealthInfoMapper healthInfoMapper;

    @GetMapping("/pdf")
    @ResponseBody
    public PDFResult createPDF() throws Exception{
        Map<String,String> map=new HashMap(130);
        ByteArrayOutputStream pdf=null;
        //获取后台数据，放入pdf（key,value），key是pdf模板中名字
        fileMap(map,GlobalData.getNowUserName());

        FileOutputStream out=null;
        String newPath=null;
        //SimpleDateFormat df = new SimpleDateFormat("yyyy_MM_dd");
        try {
            //文件名
            newPath=GlobalData.getNowUserName()+"_MedicalReport_"+".pdf";
            //保存路径
            String filePath="F:/TestData/PDFs/"+newPath;
            //模板位置
            pdf=PDFUtils.createPdfStream("./samples","samplepdf.pdf",map);

            out=new FileOutputStream(filePath);
            out.write(pdf.toByteArray());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (pdf!=null){
                try {
                    pdf.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            if (out!=null){
                try {
                    out.flush();
                    out.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return new PDFResult(111,"健康报告pdf成功生成，可以下载保存了","F:/TestData/PDFs/"+newPath);
    }

    @RequestMapping("/download")
    @ResponseBody
    public String download(HttpServletResponse response)throws IOException {
        System.out.println("开始处理下载请求...");
        File file=new File("F:/TestData/PDFs/"+GlobalData.getNowUserName()+"_MedicalReport_"+".pdf");
        try {
            download(response,file);
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return "文件不存在！请先生成报告，再打印！";
        }
    }

    private void download(HttpServletResponse response,File file){
        ServletOutputStream out=null;
        FileInputStream ips=null;
        try {
            String fileName=file.getName();
            if(!file.exists()){
                System.out.println("文件不存在！");
                return;
            }
            ips = new FileInputStream(file);
            response.setContentType("multipart/form-data");
            //设置下载后文件名
            response.addHeader("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes("UTF-8"),"ISO8859-1") + "\"");
            out=response.getOutputStream();
            //读写取文件流
            int len=0;
            byte[] buffer = new byte[1024*10];
            while ((len=ips.read(buffer))!=-1){
                out.write(buffer,0,len);
            }
            out.flush();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                out.close();
                ips.close();
            }catch (IOException e){
                System.out.println(e.getMessage());

            }
        }
    }

    private Map<String,String> fileMap(Map<String,String> map,String userName){
        try {
            System.out.println("开始生成pdf......");
            User nowUser=authUserMapper.findUserByName(userName);
            List<Doctor> doctors=doctorInfoMapper.findDoctorByUserName(userName);
            List<HealthHistory> healthHistories=healthInfoMapper.selectHealthHistoryByUserName(userName);
            List<TreatHistory> treatHistories=healthInfoMapper.selectTreatHistoryByUserName(userName);
            List<Plan> plans=healthInfoMapper.selectPlansByUserName(userName);
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
            String doctorInfo="";
            String healthHistoryInfo="";
            String treatHistoryInfo="";
            String planInfo="";
            for(Doctor doctor:doctors){
                doctorInfo+="医生名："+doctor.getDoctorName()+"\n诊疗时间："+doctor.getTreatTime()+"\n负责疾病："+doctor.getDepartment()+"\n\n";
            }
            for (HealthHistory healthHistory:healthHistories){
                healthHistoryInfo+="检查时间："+healthHistory.getCheckTime()+"\n检查结果："+healthHistory.getCheckResult()+"\n已经治愈："
                        +(healthHistory.getHasTreat()==1?"是":"否")+"\n\n";
            }
            for (TreatHistory treatHistory:treatHistories){
                treatHistoryInfo+="诊疗时间："+treatHistory.getTreatTime()+"\n病症："+treatHistory.getTreatDisease()+"\n主治医师："+treatHistory.getTreatDoctor()+
                        "\n已经治愈："+(treatHistory.getHasCure()==1?"是":"否")+"\n\n";
            }
            for (Plan plan:plans){
                String planTime = "";
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
                planInfo+="计划时间："+planTime+"\n计划项目："+plan.getPlanProject()+"\n\n";
            }

            map.put("name",nowUser.getTrueName());
            map.put("sex",nowUser.getSex()>0?"男":"女");
            map.put("birthday",nowUser.getBirthDay());
            map.put("anaphylatic",nowUser.getAnaphylactic());
            map.put("medihistory",nowUser.getMedicalHistory());
            map.put("bloodtype",bloodType);

            map.put("doctor",doctorInfo);
            map.put("healthhistory",healthHistoryInfo);
            map.put("treathistory",treatHistoryInfo);
            map.put("plans",planInfo);

        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }
}
