package com.graduationproject.healthmanager.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class TxtUtils {
    public static ArrayList<String> getTxt(String filePath){
        try {
            String tmp=null;
            File f = new File(filePath);
            //String adn="";
            InputStreamReader read=new InputStreamReader(new FileInputStream(f), StandardCharsets.UTF_8);
            ArrayList<String> readList= new ArrayList<>();
            //ArrayList<String> retList=new ArrayList<String>();
            BufferedReader reader=new BufferedReader(read);
            while ((tmp=reader.readLine())!=null&&!"".equals(tmp)){
                readList.add(tmp);
            }
            read.close();
            return readList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
