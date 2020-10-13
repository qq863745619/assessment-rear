package com.nju.software.assessment.model;

import javax.persistence.Column;
import java.util.Date;

public class DistributionDetailModel {

    private String distributionId;

    @Override
    public String toString() {
        return "DistributionDetailModel{" +
                "distributionId='" + distributionId + '\'' +
                ", distributionState=" + distributionState +
                ", distributionTime=" + distributionTime +
                ", distributionDetailId='" + distributionDetailId + '\'' +
                ", objectId=" + objectId +
                ", objectNum=" + objectNum +
                '}';
    }

    public String getDistributionId() {
        return distributionId;
    }

    public void setDistributionId(String distributionId) {
        this.distributionId = distributionId;
    }

    public Integer getDistributionState() {
        return distributionState;
    }

    public void setDistributionState(Integer distributionState) {
        this.distributionState = distributionState;
    }

    public Date getDistributionTime() {
        return distributionTime;
    }

    public void setDistributionTime(Date distributionTime) {
        this.distributionTime = distributionTime;
    }

    public String getDistributionDetailId() {
        return distributionDetailId;
    }

    public void setDistributionDetailId(String distributionDetailId) {
        this.distributionDetailId = distributionDetailId;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public Integer getObjectNum() {
        return objectNum;
    }

    public void setObjectNum(Integer objectNum) {
        this.objectNum = objectNum;
    }

    private Integer distributionState;

    public DistributionDetailModel(String distributionId, Integer distributionState, Date distributionTime, String distributionDetailId, Long objectId, Integer objectNum) {
        this.distributionId = distributionId;
        this.distributionState = distributionState;
        this.distributionTime = distributionTime;
        this.distributionDetailId = distributionDetailId;
        this.objectId = objectId;
        this.objectNum = objectNum;
    }

    private Date distributionTime;

    private String distributionDetailId;

    private Long objectId;

    private Integer objectNum;
}
