package com.graduationproject.healthmanager.model;

public class InitDoctor {
    public InitDoctor(int flag, String tip) {
        this.flag = flag;
        this.tip = tip;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    private int flag;
    private String tip;
}
