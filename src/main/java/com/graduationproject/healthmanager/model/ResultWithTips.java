package com.graduationproject.healthmanager.model;

public class ResultWithTips extends Result {
    public ResultWithTips(int code, String message, String tips) {
        super(code, message);
        this.tips = tips;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    private String tips;
}
