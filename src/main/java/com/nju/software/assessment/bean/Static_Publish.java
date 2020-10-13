package com.nju.software.assessment.bean;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;
import java.util.Map;

@Document
public class Static_Publish {
    @Id
    private String id;
     private Integer type;
     private String title;
     private String note;
     private String department;
     private String date;
     private String startDate;
     private String endDate;
     private String publisher;
     private Integer level;
     private Integer value;
     //被测评人
     private List<String> bcp_namelist;
     //测评人
     private List<String> cp_namelist;
     private List<List<String>> cpx;
     private String createDate;
     private List<List<String>> legend;
     private List<Integer> step;
     private List<Map> bz;
     //前置条件,百分之多少
     private Integer ratio;
     //测评信息
     private String cpxx;
     private String xxx;
     private String tableData;
     private List<Double> widths;

    public String getXxx() {
        return xxx;
    }

    public void setXxx(String xxx) {
        this.xxx = xxx;
    }

    public List<Double> getWidths() {
        return widths;
    }

    public void setWidths(List<Double> widths) {
        this.widths = widths;
    }

    public String getTableData() {
        return tableData;
    }

    public void setTableData(String tableData) {
        this.tableData = tableData;
    }

    public String getCpxx() {
        return cpxx;
    }

    public void setCpxx(String cpxx) {
        this.cpxx = cpxx;
    }

    public Integer getRatio() {
        return ratio;
    }

    public void setRatio(Integer ratio) {
        this.ratio = ratio;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Map> getBz() {
        return bz;
    }

    public void setBz(List<Map> bz) {
        this.bz = bz;
    }

    public List<Integer> getStep() {
        return step;
    }

    public void setStep(List<Integer> step) {
        this.step = step;
    }

    public List<List<String>> getLegend() {
        return legend;
    }

    public void setLegend(List<List<String>> legend) {
        this.legend = legend;
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

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public List<String> getBcp_namelist() {
        return bcp_namelist;
    }

    public void setBcp_namelist(List<String> bcp_namelist) {
        this.bcp_namelist = bcp_namelist;
    }

    public List<String> getCp_namelist() {
        return cp_namelist;
    }

    public void setCp_namelist(List<String> cp_namelist) {
        this.cp_namelist = cp_namelist;
    }

    public List<List<String>> getCpx() {
        return cpx;
    }

    public void setCpx(List<List<String>> cpx) {
        this.cpx = cpx;
    }

    public Static_Publish() {
    }

    public Static_Publish(String id, Integer type, String title, String note, String department, String date, String startDate, String endDate, String publisher, Integer level, Integer value, List<String> bcp_namelist, List<String> cp_namelist, List<List<String>> cpx, String createDate, List<List<String>> legend, List<Integer> step, List<Map> bz, Integer ratio, String cpxx, String xxx, String tableData, List<Double> widths) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.note = note;
        this.department = department;
        this.date = date;
        this.startDate = startDate;
        this.endDate = endDate;
        this.publisher = publisher;
        this.level = level;
        this.value = value;
        this.bcp_namelist = bcp_namelist;
        this.cp_namelist = cp_namelist;
        this.cpx = cpx;
        this.createDate = createDate;
        this.legend = legend;
        this.step = step;
        this.bz = bz;
        this.ratio = ratio;
        this.cpxx = cpxx;
        this.xxx = xxx;
        this.tableData = tableData;
        this.widths = widths;
    }
}
