package com.graduationproject.healthmanager.model;

public class Habits {
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(String sleepTime) {
        this.sleepTime = sleepTime;
    }

    public String getWeakUpTime() {
        return weakUpTime;
    }

    public void setWeakUpTime(String weakUpTime) {
        this.weakUpTime = weakUpTime;
    }

    public int getMeals() {
        return meals;
    }

    public void setMeals(int meals) {
        this.meals = meals;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    private String userName;
    private String sleepTime;
    private String weakUpTime;
    private int meals;
    private int pressure;
}
