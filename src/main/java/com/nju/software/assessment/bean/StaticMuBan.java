package com.nju.software.assessment.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="static_muban")
public class StaticMuBan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userid;
    //类型，1-7
    private Integer type;

    //备注或说明
    private String note;
    //日期
    private String date;
    //部门
    private String department;

    //被投票人
    private String namelist;

    public StaticMuBan(Integer userid, Integer type, String note, String date, String department, String namelist) {
        this.userid = userid;
        this.type = type;
        this.note = note;
        this.date = date;
        this.department = department;
        this.namelist = namelist;
    }

    public StaticMuBan() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getNamelist() {
        return namelist;
    }

    public void setNamelist(String namelist) {
        this.namelist = namelist;
    }
}
