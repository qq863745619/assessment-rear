package com.nju.software.assessment.query;

import java.util.List;

public class staticPublishCondition {
    private Integer pageNum;
    private Integer pageSize;
    private String publisher;
    private String title;
    private String name;
    private String isReform;
    private String isShow;
    private List<Integer> levels;
    private List<String> publishdateRange ;
    private List<String> startdateRange ;
    private List<String> enddateRange ;
    private List<String> tpdateRange;
    private List<Integer> tpRatio;
    private List<Integer> qzRatio;
    private List<String> status;

    public staticPublishCondition(Integer pageNum, Integer pageSize, String publisher, String title, String name, String isReform, String isShow, List<Integer> levels, List<String> publishdateRange, List<String> startdateRange, List<String> enddateRange, List<String> tpdateRange, List<Integer> tpRatio, List<Integer> qzRatio, List<String> status) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.publisher = publisher;
        this.title = title;
        this.name = name;
        this.isReform = isReform;
        this.isShow = isShow;
        this.levels = levels;
        this.publishdateRange = publishdateRange;
        this.startdateRange = startdateRange;
        this.enddateRange = enddateRange;
        this.tpdateRange = tpdateRange;
        this.tpRatio = tpRatio;
        this.qzRatio = qzRatio;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public staticPublishCondition() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsReform() {
        return isReform;
    }

    public void setIsReform(String isReform) {
        this.isReform = isReform;
    }

    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }

    public List<Integer> getLevels() {
        return levels;
    }

    public void setLevels(List<Integer> levels) {
        this.levels = levels;
    }

    public List<String> getPublishdateRange() {
        return publishdateRange;
    }

    public void setPublishdateRange(List<String> publishdateRange) {
        this.publishdateRange = publishdateRange;
    }

    public List<String> getStartdateRange() {
        return startdateRange;
    }

    public void setStartdateRange(List<String> startdateRange) {
        this.startdateRange = startdateRange;
    }

    public List<String> getEnddateRange() {
        return enddateRange;
    }

    public void setEnddateRange(List<String> enddateRange) {
        this.enddateRange = enddateRange;
    }

    public List<Integer> getTpRatio() {
        return tpRatio;
    }

    public void setTpRatio(List<Integer> tpRatio) {
        this.tpRatio = tpRatio;
    }

    public List<Integer> getQzRatio() {
        return qzRatio;
    }

    public void setQzRatio(List<Integer> qzRatio) {
        this.qzRatio = qzRatio;
    }

    public List<String> getStatus() {
        return status;
    }

    public void setStatus(List<String> status) {
        this.status = status;
    }

    public List<String> getTpdateRange() {
        return tpdateRange;
    }

    public void setTpdateRange(List<String> tpdateRange) {
        this.tpdateRange = tpdateRange;
    }
}
