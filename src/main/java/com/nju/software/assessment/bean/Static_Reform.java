package com.nju.software.assessment.bean;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;
import java.util.Map;
//发布的投票信息实体
@Document
public class Static_Reform {
    @Id
    private String id;
    private String staticPublishId;
    private String title;//投票标题
    private String note;//投票的备注
    private String department;
    private String startDate;//投票开始日期
    private String publisher;//发布者
    private Integer level;//投票级别
    private Integer value;
    private Integer ratio;//前置条件
    private String name;//接收到投票通知的人
    private Map<String,List<List<String>>> reform;//本人投票的结果信息
    private String isReform;//本人是否已经投票
    private String date;//投票时间
    private String createDate;//投票创建时间
    private String endDate;//投票结束日期
    private String show;
    private List<String> bz;//备注
    public Static_Reform() {
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public List<String> getBz() {
        return bz;
    }

    public void setBz(List<String> bz) {
        this.bz = bz;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getRatio() {
        return ratio;
    }

    public void setRatio(Integer ratio) {
        this.ratio = ratio;
    }

    public Static_Reform(String id, String staticPublishId, String title, String note, String department, String startDate, String publisher, Integer level, Integer value, Integer ratio, String name, Map<String, List<List<String>>> reform, String isReform, String date, String createDate, String endDate, String show, List<String> bz) {
        this.id = id;
        this.staticPublishId = staticPublishId;
        this.title = title;
        this.note = note;
        this.department = department;
        this.startDate = startDate;
        this.publisher = publisher;
        this.level = level;
        this.value = value;
        this.ratio = ratio;
        this.name = name;
        this.reform = reform;
        this.isReform = isReform;
        this.date = date;
        this.createDate = createDate;
        this.endDate = endDate;
        this.show = show;
        this.bz = bz;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getShow() {
        return show;
    }

    public void setShow(String show) {
        this.show = show;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStaticPublishId() {
        return staticPublishId;
    }

    public void setStaticPublishId(String staticPublishId) {
        this.staticPublishId = staticPublishId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsReform() {
        return isReform;
    }

    public void setIsReform(String isReform) {
        this.isReform = isReform;
    }

    public Map<String,List<List<String>>> getReform() {
        return reform;
    }

    public void setReform(Map<String,List<List<String>>> reform) {
        this.reform = reform;
    }
}
