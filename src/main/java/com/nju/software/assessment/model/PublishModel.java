package com.nju.software.assessment.model;

import com.nju.software.assessment.bean.Publish;
import com.nju.software.assessment.util.DateUtil;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;

public class PublishModel {
    private String content;
    private String publisher;
    private String title;
    private  String [] peoplename;
    private int level;
    private int value;
    private String mianshu;
    private String startDate;
    private String endDate;
    private String createDate;

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public PublishModel(String content, String publisher, String title, String[] peoplename, int level, int value, String mianshu, String startDate, String endDate) {
        this.content = content;
        this.publisher = publisher;
        this.title = title;
        this.peoplename = peoplename;
        this.level = level;
        this.value = value;
        this.mianshu = mianshu;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public PublishModel() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getPeoplename() {
        return peoplename;
    }

    public void setPeoplename(String[] peoplename) {
        this.peoplename = peoplename;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getMianshu() {
        return mianshu;
    }

    public void setMianshu(String mianshu) {
        this.mianshu = mianshu;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "PublishModel{" +
                "content='" + content + '\'' +
                ", publisher='" + publisher + '\'' +
                ", title='" + title + '\'' +
                ", peoplename=" + Arrays.toString(peoplename) +
                ", level=" + level +
                ", value=" + value +
                ", mianshu='" + mianshu + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
//    public static Publish convert(PublishModel publishModel,int type) throws ParseException {
//        return new Publish(publishModel.getPublisher(),publishModel.getTitle(),publishModel.getMianshu()
//        ,publishModel.getContent(),publishModel.getLevel(),publishModel.getValue(), DateUtil.format(publishModel.getCreateDate()),type,DateUtil.format(publishModel.getStartDate()),DateUtil.format(publishModel.getEndDate()));
//
//    }
}
