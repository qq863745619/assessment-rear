package com.nju.software.assessment.model;

public class LoginInfo {

    //用户编号
    private Integer yhbh;
    //用户名称
    private String yhmc;
    //用户代码（名字拼音缩写）
    private String yhdm;
    //用户密码
    private String yhkl;
    //用户职务
    private String  yhsf;

    public LoginInfo(Integer yhbh, String yhmc, String yhdm, String yhkl, String yhsf) {
        this.yhbh = yhbh;
        this.yhmc = yhmc;
        this.yhdm = yhdm;
        this.yhkl = yhkl;
        this.yhsf = yhsf;
    }

    public LoginInfo() {
    }

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

    public String getYhdm() {
        return yhdm;
    }

    public void setYhdm(String yhdm) {
        this.yhdm = yhdm;
    }

    public String getYhkl() {
        return yhkl;
    }

    public void setYhkl(String yhkl) {
        this.yhkl = yhkl;
    }

    public String getYhsf() {
        return yhsf;
    }

    public void setYhsf(String yhsf) {
        this.yhsf = yhsf;
    }
}
