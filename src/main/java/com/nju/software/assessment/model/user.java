package com.nju.software.assessment.model;

public class user {
    private String username;
    private String password;
    private String verifyCode;

    public user(String username, String password,String verifyCode) {
        this.username = username;
        this.password = password;
        this.verifyCode = verifyCode;
    }

    public user() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }
}
