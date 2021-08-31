package com.graduationproject.healthmanager.model;

public class GlobalData {
    public static String getNowUserName() {
        return nowUserName;
    }

    public static void setNowUserName(String nowUserName) {
        GlobalData.nowUserName = nowUserName;
    }


    public static String getNowUserPasswd() {
        return nowUserPasswd;
    }

    public static void setNowUserPasswd(String nowUserPasswd) {
        GlobalData.nowUserPasswd = nowUserPasswd;
    }

    private static String nowUserName;
    private static String nowUserPasswd;
}
