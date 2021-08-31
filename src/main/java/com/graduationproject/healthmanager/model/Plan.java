package com.graduationproject.healthmanager.model;

public class Plan {
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getPlanTime() {
        return planTime;
    }

    public void setPlanTime(int planTime) {
        this.planTime = planTime;
    }

    public String getPlanDate() {
        return planDate;
    }

    public void setPlanDate(String planDate) {
        this.planDate = planDate;
    }

    public String getPlanProject() {
        return planProject;
    }

    public void setPlanProject(String planProject) {
        this.planProject = planProject;
    }

    private String userName;
    private int planTime;
    private String planDate;
    private String planProject;
}
