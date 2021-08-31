package com.graduationproject.healthmanager.model;

public class Password {
    public String getOldPaswd() {
        return oldPaswd;
    }

    public void setOldPaswd(String oldPaswd) {
        this.oldPaswd = oldPaswd;
    }

    public String getNewPaswd() {
        return newPaswd;
    }

    public void setNewPaswd(String newPaswd) {
        this.newPaswd = newPaswd;
    }

    private String oldPaswd;
    private String newPaswd;
}
