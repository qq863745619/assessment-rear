package com.nju.software.assessment.model;

import java.util.List;

public class retInfo {
    private String token;
    private String name;
    private String roles;
    private String deptonName;
    private List<Long> qxbh;

    public retInfo(String token, String yhmc, String roles, String dmms, String yhbm, Integer yhbh, List<Long> qxbh) {
        this.token = token;
        this.name = yhmc;
        this.roles = roles;
        this.deptonName = dmms;
        this.depton = yhbm;
        this.yhbh = yhbh;
        this.qxbh = qxbh;
    }

    public List<Long> getQxbh() {
        return qxbh;
    }

    public void setQxbh(List<Long> qxbh) {
        this.qxbh = qxbh;
    }








    public String getDeptonName() {
        return deptonName;
    }

    public void setDeptonName(String deptonName) {
        this.deptonName = deptonName;
    }




    public String getDepton() {
        return depton;
    }

    public void setDepton(String depton) {
        this.depton = depton;
    }

    private String depton;

    public Integer getYhbh() {
        return yhbh;
    }

    public void setYhbh(Integer yhbh) {
        this.yhbh = yhbh;
    }

    private Integer yhbh;
    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public retInfo(String token, String name, String roles,Integer yhbh) {
        this.token = token;
        this.name = name;
        this.roles = roles;
        this.yhbh = yhbh;

    }

    public retInfo() {
    }



}
