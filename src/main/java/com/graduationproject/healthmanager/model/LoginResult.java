package com.graduationproject.healthmanager.model;

public class LoginResult extends Result {
    public LoginResult(int code, String token) {
        super(code);
        this.token = token;
    }

    public LoginResult(int code, String token, String message) {
        super(code, message);
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String token;
}
