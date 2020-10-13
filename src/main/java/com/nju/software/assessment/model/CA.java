package com.nju.software.assessment.model;

public class CA {
    private String yhdm;
    private String yhkl;
    private String verifyCode;

    public CA() {
    }

    public CA(String yhdm, String yhkl,String verifyCode) {
        this.yhdm = yhdm;
        this.yhkl = yhkl;
        this.verifyCode = verifyCode;
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

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }
}
