package com.nju.software.assessment.bean;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

@Document
public class Publish {
    @Id
    private String id;
    //表单标题
    private String title;
    //表单描述
    private String miaoshu;
    //组件的个数
    private Integer num;
    //表单组件标题
    private List<Object> inputbt;
    //表单组件类型
    private List<Object> optiontype;
    //单选按钮的选项个数
    private List<Object> radionum;
    //单选按钮名字
    private String radioname;
    //多选按钮的选项个数
    private List<Object> checkboxnum;
    //多选按钮名字
    private String checkboxname;
    //下拉框的选项个数
    private List<Object> selectnum;
    //下拉框名字
    private String selectname;
    //被投人列表
    private String[] peoplename;
    //投票的级别（4个等级）
    private Integer jibie;

    private Integer value;
    //创建时间
    private String createTime;
    //投票开始时间
    private String startTime;
    //投票结束时间
    private String endTime;
    //发布者
    private String publisher;

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String[] getPeoplename() {
        return peoplename;
    }

    public void setPeoplename(String[] peoplename) {
        this.peoplename = peoplename;
    }

    public Integer getJibie() {
        return jibie;
    }

    public void setJibie(Integer jibie) {
        this.jibie = jibie;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMiaoshu() {
        return miaoshu;
    }

    public void setMiaoshu(String miaoshu) {
        this.miaoshu = miaoshu;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public List<Object> getInputbt() {
        return inputbt;
    }

    public void setInputbt(List<Object> inputbt) {
        this.inputbt = inputbt;
    }

    public List<Object> getOptiontype() {
        return optiontype;
    }

    public void setOptiontype(List<Object> optiontype) {
        this.optiontype = optiontype;
    }

    public List<Object> getRadionum() {
        return radionum;
    }

    public void setRadionum(List<Object> radionum) {
        this.radionum = radionum;
    }

    public String getRadioname() {
        return radioname;
    }

    public void setRadioname(String radioname) {
        this.radioname = radioname;
    }

    public List<Object> getCheckboxnum() {
        return checkboxnum;
    }

    public void setCheckboxnum(List<Object> checkboxnum) {
        this.checkboxnum = checkboxnum;
    }

    public String getCheckboxname() {
        return checkboxname;
    }

    public void setCheckboxname(String checkboxname) {
        this.checkboxname = checkboxname;
    }

    public List<Object> getSelectnum() {
        return selectnum;
    }

    public void setSelectnum(List<Object> selectnum) {
        this.selectnum = selectnum;
    }

    public String getSelectname() {
        return selectname;
    }

    public void setSelectname(String selectname) {
        this.selectname = selectname;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Publish() {
    }

    public Publish(String id, String title, String miaoshu, Integer num, List<Object> inputbt, List<Object> optiontype, List<Object> radionum, String radioname, List<Object> checkboxnum, String checkboxname, List<Object> selectnum, String selectname, String[] peoplename, Integer jibie, Integer value, String createTime, String startTime, String endTime, String publisher, String type) {
        this.id = id;
        this.title = title;
        this.miaoshu = miaoshu;
        this.num = num;
        this.inputbt = inputbt;
        this.optiontype = optiontype;
        this.radionum = radionum;
        this.radioname = radioname;
        this.checkboxnum = checkboxnum;
        this.checkboxname = checkboxname;
        this.selectnum = selectnum;
        this.selectname = selectname;
        this.peoplename = peoplename;
        this.jibie = jibie;
        this.value = value;
        this.createTime = createTime;
        this.startTime = startTime;
        this.endTime = endTime;
        this.publisher = publisher;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Publish{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", miaoshu='" + miaoshu + '\'' +
                ", num=" + num +
                ", inputbt=" + inputbt +
                ", optiontype=" + optiontype +
                ", radionum=" + radionum +
                ", radioname='" + radioname + '\'' +
                ", checkboxnum=" + checkboxnum +
                ", checkboxname='" + checkboxname + '\'' +
                ", selectnum=" + selectnum +
                ", selectname='" + selectname + '\'' +
                ", peoplename=" + Arrays.toString(peoplename) +
                ", jibie=" + jibie +
                ", value=" + value +
                ", createTime='" + createTime + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", publisher='" + publisher + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
