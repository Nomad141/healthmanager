package com.graduationproject.healthmanager.model;

public class InitHealth {

    public InitHealth(int flag1, int flag2, int flag3, String tip) {
        this.flag1 = flag1;
        this.flag2 = flag2;
        this.flag3 = flag3;
        this.tip = tip;
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

    public int getFlag3() {
        return flag3;
    }

    public void setFlag3(int flag3) {
        this.flag3 = flag3;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    private int flag1;
    private int flag2;
    private int flag3;
    private String tip;
}
