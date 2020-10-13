package com.nju.software.assessment.bean2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "PUB_DMB")//部门表
public class DMB implements Serializable {

    //LBBH='USR206-99'代表部门
    @Column(name = "LBBH")
    private String lbbh;

    //部门的编号
    @Id
    @Column(name = "DMBH")
    private String dmbh;

    //部门的名称
    @Column(name = "DMMS")
    private String dmms;


    public DMB() {
    }

    public DMB(String lbbh,String dmbh, String dmms) {
        this.lbbh = lbbh;
        this.dmbh = dmbh;
        this.dmms = dmms;
    }

    public String getDmbh() {
        return dmbh;
    }

    public void setDmbh(String dmbh) {
        this.dmbh = dmbh;
    }

    public String getDmms() {
        return dmms;
    }

    public void setDmms(String dmms) {
        this.dmms = dmms;
    }

    public String getLbbh() {
        return lbbh;
    }

    public void setLbbh(String lbbh) {
        this.lbbh = lbbh;
    }

    @Override
    public String toString() {
        return "DMB{" +
                "dmbh='" + dmbh + '\'' +
                ", dmms='" + dmms + '\'' +
                '}';
    }
}


