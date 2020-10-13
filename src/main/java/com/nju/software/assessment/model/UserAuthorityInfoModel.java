package com.nju.software.assessment.model;

import com.nju.software.assessment.bean.Authority;
import com.nju.software.assessment.bean2.DMB;
import com.nju.software.assessment.bean2.YHB;

import java.util.Date;

public class UserAuthorityInfoModel {
    private Long qxbh;

    private String qxmc;

    private String qxsm;

    private Date qxcjsj;

    private Integer yhbh;

    public Long getQxbh() {
        return qxbh;
    }

    public void setQxbh(Long qxbh) {
        this.qxbh = qxbh;
    }

    public String getQxmc() {
        return qxmc;
    }

    public void setQxmc(String qxmc) {
        this.qxmc = qxmc;
    }

    public String getQxsm() {
        return qxsm;
    }

    public void setQxsm(String qxsm) {
        this.qxsm = qxsm;
    }

    public Date getQxcjsj() {
        return qxcjsj;
    }

    public void setQxcjsj(Date qxcjsj) {
        this.qxcjsj = qxcjsj;
    }

    public Integer getYhbh() {
        return yhbh;
    }

    public void setYhbh(Integer yhbh) {
        this.yhbh = yhbh;
    }

    public String getYhdm() {
        return yhdm;
    }

    public void setYhdm(String yhdm) {
        this.yhdm = yhdm;
    }

    public String getYhmc() {
        return yhmc;
    }

    public void setYhmc(String yhmc) {
        this.yhmc = yhmc;
    }

    public String getYhkl() {
        return yhkl;
    }

    public void setYhkl(String yhkl) {
        this.yhkl = yhkl;
    }

    public String getYhbm() {
        return yhbm;
    }

    public void setYhbm(String yhbm) {
        this.yhbm = yhbm;
    }

    public String getYhsf() {
        return yhsf;
    }

    public void setYhsf(String yhsf) {
        this.yhsf = yhsf;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getYhqxbh() {
        return yhqxbh;
    }

    public void setYhqxbh(Long yhqxbh) {
        this.yhqxbh = yhqxbh;
    }

    //用户代码（名字拼音缩写）
    private String yhdm;

    //用户名称
    private String yhmc;

    //用户密码
    private String yhkl;

    //用户部门编号
    private String yhbm;

    //用户职务
    private String yhsf;

    //用户手机号
    private String phone;

    private Long yhqxbh;

    public String getBmmc() {
        return bmmc;
    }

    public void setBmmc(String bmmc) {
        this.bmmc = bmmc;
    }

    private  String bmmc;
    public UserAuthorityInfoModel(Long qxbh, String qxmc, String qxsm, Date qxcjsj, Integer yhbh, String yhdm, String yhmc, String yhkl, String yhbm, String yhsf, String phone, Long yhqxbh) {
        this.qxbh = qxbh;
        this.qxmc = qxmc;
        this.qxsm = qxsm;
        this.qxcjsj = qxcjsj;
        this.yhbh = yhbh;
        this.yhdm = yhdm;
        this.yhmc = yhmc;

        this.yhbm = yhbm;
        this.yhsf = yhsf;
        this.phone = phone;
        this.yhqxbh = yhqxbh;
    }

    public UserAuthorityInfoModel(Authority authority, YHB yhb, DMB dmb,Long yhqxbh) {
        this.qxbh = authority.getQxbh();
        this.qxmc = authority.getQxmc();
        this.qxsm = authority.getQxsm();
        this.qxcjsj = authority.getQxcjsj();
        this.yhbh = yhb.getYhbh();
        this.yhdm = yhb.getYhdm();
        this.yhmc = yhb.getYhmc();
       // this.yhkl = yhb.getYhkl();
        this.yhbm = yhb.getYhbm();
        this.yhsf = yhb.getYhsf();
        this.phone = yhb.getPhone();
        this.yhqxbh = yhqxbh;
        this.bmmc = dmb.getDmms();
    }


    @Override
    public String toString() {
        return "UserAuthorityInfoModel{" +
                "qxbh=" + qxbh +
                ", qxmc='" + qxmc + '\'' +
                ", qxsm='" + qxsm + '\'' +
                ", qxcjsj=" + qxcjsj +
                ", yhbh=" + yhbh +
                ", yhdm='" + yhdm + '\'' +
                ", yhmc='" + yhmc + '\'' +
                ", yhkl='" + yhkl + '\'' +
                ", yhbm='" + yhbm + '\'' +
                ", yhsf='" + yhsf + '\'' +
                ", phone='" + phone + '\'' +
                ", yhqxbh=" + yhqxbh +
                '}';
    }

}
