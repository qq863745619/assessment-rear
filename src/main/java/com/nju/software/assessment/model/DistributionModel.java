package com.nju.software.assessment.model;

public class DistributionModel {
    private String id;

    private String listId;

    private String createTime;

    private int state;

    private String user;

    private String userid;

    public DistributionModel(String id, String listId, String createTime, int state, String user, String userid, String statename, String name, int type) {
        this.id = id;
        this.listId = listId;
        this.createTime = createTime;
        this.state = state;
        this.user = user;
        this.userid = userid;
        this.statename = statename;
        this.name = name;
        this.type = type;
    }

    private String statename;

    private String name;

    private int type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getListId() {
        return listId;
    }

    public void setListId(String listId) {
        this.listId = listId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getStatename() {
        return statename;
    }

    public void setStatename(String statename) {
        this.statename = statename;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
