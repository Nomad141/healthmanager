package com.graduationproject.healthmanager.model;

public class TreatHistory {
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTreatTime() {
        return treatTime;
    }

    public void setTreatTime(String treatTime) {
        this.treatTime = treatTime;
    }

    public String getTreatDoctor() {
        return treatDoctor;
    }

    public void setTreatDoctor(String treatDoctor) {
        this.treatDoctor = treatDoctor;
    }

    public int getHasCure() {
        return hasCure;
    }

    public void setHasCure(int hasCure) {
        this.hasCure = hasCure;
    }

    public String getTreatDisease() {
        return treatDisease;
    }

    public void setTreatDisease(String treatDisease) {
        this.treatDisease = treatDisease;
    }

    private String userName;
    private String treatTime;
    private String treatDisease;
    private String treatDoctor;
    private int hasCure;
}
