package com.nju.software.assessment.model;

import java.io.Serializable;

public class YhbModel implements Serializable {

    private Integer yhbh;

    //用户名称
    private String yhmc;



    //用户职务  dyd add
    private String yhsf;

    public YhbModel() {
    }

    public YhbModel(Integer yhbh, String yhmc, String yhsf) {
        this.yhbh = yhbh;
        this.yhmc = yhmc;
        this.yhsf = yhsf;
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

    public String getYhsf() {
        return yhsf;
    }

    public void setYhsf(String yhsf) {
        this.yhsf = yhsf;
    }
}
