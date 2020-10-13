package com.nju.software.assessment.model;

import sun.dc.pr.PRError;

public class Yh {


    private int yhbh;
    private String yhmc;
    private String password;
    private String  yhsf;


    public Integer getYhbh() {
        return yhbh;
    }

    public void setYhbh(Integer yhbh) {
        this.yhbh = yhbh;
    }
    public String getYhmc() {
        return yhmc;
    }

    public void setYhmc(String yhmc) {
        this.yhmc = yhmc;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getYhsf() {
        return yhsf;
    }

    public void setYhsf(String yhsf) {
        this.yhsf = yhsf;
    }

    public Yh(String yhmc, String password, String yhsf,Integer yhbh) {
        this.yhmc = yhmc;
        this.password = password;
        this.yhsf = yhsf;
        this.yhbh = yhbh;
    }

    public Yh() {
    }
}
