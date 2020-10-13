package com.nju.software.assessment.bean2;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PUB_XTGL_YHB")
public class YHB implements Serializable{
    //用户编号
    @Id
    @Column(name = "YHBH")
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer yhbh;

    //用户代码（名字拼音缩写）
    @Column(name = "YHDM")
    private String yhdm;

    //用户名称
    @Column(name = "YHMC")
    private String yhmc;

    //用户密码
    @Column(name = "YHKL")
    private String yhkl;

    //用户部门编号
    @Column(name = "YHBM")
    private String yhbm;

    //用户职务
    @Column(name = "YHSF")
    private String yhsf;

    //用户手机号
    @Column(name = "PHONE")
    private String phone;

    public YHB() {
    }

    public YHB(String yhmc) {
        this.yhbh = yhbh;
        this.yhmc = yhmc;
    }

    public YHB(Integer yhbh, String yhdm, String yhmc, String yhkl, String yhbm, String yhsf, String phone) {
        this.yhbh = yhbh;
        this.yhdm = yhdm;
        this.yhmc = yhmc;
        this.yhkl = yhkl;
        this.yhbm = yhbm;
        this.yhsf = yhsf;
        this.phone = phone;
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

    @Override
    public String toString() {
        return "YHB{" +
                "yhbh=" + yhbh +
                ", yhdm='" + yhdm + '\'' +
                ", yhmc='" + yhmc + '\'' +
                ", yhkl='" + yhkl + '\'' +
                ", yhbm='" + yhbm + '\'' +
                ", yhsf='" + yhsf + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}

