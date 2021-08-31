package com.graduationproject.healthmanager.model;

public class InitPersonal {
    public InitPersonal(int flag1, int flag2) {
        this.flag1 = flag1;
        this.flag2 = flag2;
    }

    public InitPersonal() {
    }

    public int getFlag1() {
        return flag1;
    }

    public void setFlag1(int flag1) {
        this.flag1 = flag1;
    }

    public int getFlag2() {
        return flag2;
    }

    public void setFlag2(int flag2) {
        this.flag2 = flag2;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    private int flag1;
    private int flag2;
    private String tip;
}
