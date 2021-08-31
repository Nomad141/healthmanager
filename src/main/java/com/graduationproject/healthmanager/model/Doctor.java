package com.graduationproject.healthmanager.model;

public class Doctor {
    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getTreatUser() {
        return treatUser;
    }

    public void setTreatUser(String treatUser) {
        this.treatUser = treatUser;
    }

    public String getTreatTime() {
        return treatTime;
    }

    public void setTreatTime(String treatTime) {
        this.treatTime = treatTime;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    private String doctorName;
    private String treatUser;
    private String treatTime;
    private String department;
}
