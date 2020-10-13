package com.nju.software.assessment.bean;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

@Document
public class Form {
    //自定义表单id
    @Id
    private Integer id;
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


    public Form() {
    }

    public Form(Integer id, String title, String miaoshu, Integer num, List<Object> inputbt, List<Object> optiontype, List<Object> radionum, String radioname, List<Object> checkboxnum, String checkboxname, List<Object> selectnum, String selectname) {
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
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
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

    @Override
    public String toString() {
        return "Form{" +
                "id=" + id +
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
                '}';
    }
}
