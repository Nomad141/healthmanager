package com.graduationproject.healthmanager.model;

public class HealthHistory {
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }

    public String getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult;
    }

    public int getHasTreat() {
        return hasTreat;
    }

    public void setHasTreat(int hasTreat) {
        this.hasTreat = hasTreat;
    }

    private String userName;
    private String checkTime;
    private String checkResult;
    private int hasTreat;
}
