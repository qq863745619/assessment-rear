package com.nju.software.assessment.bean;

import javax.persistence.*;

@Entity
@Table(name = "Yitou")
public class yitou {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    //发布者
    private String fbz;
    //级别
    private String level;
    //发布时间
    private String fbsj;
    //截止时间
    private String jzsj;

    public yitou(String name, String fbz, String level, String fbsj, String jzsj) {
        this.name = name;
        this.fbz = fbz;
        this.level = level;
        this.fbsj = fbsj;
        this.jzsj = jzsj;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFbz() {
        return fbz;
    }

    public void setFbz(String fbz) {
        this.fbz = fbz;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getFbsj() {
        return fbsj;
    }

    public void setFbsj(String fbsj) {
        this.fbsj = fbsj;
    }

    public String getJzsj() {
        return jzsj;
    }

    public void setJzsj(String jzsj) {
        this.jzsj = jzsj;
    }

    @Override
    public String toString() {
        return "yitou{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", from='" + fbz + '\'' +
                ", level='" + level + '\'' +
                ", fbsj='" + fbsj + '\'' +
                ", jzsj='" + jzsj + '\'' +
                '}';
    }

    public yitou() {
    }
}
