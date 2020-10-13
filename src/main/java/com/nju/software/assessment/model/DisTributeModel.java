package com.nju.software.assessment.model;

import com.nju.software.assessment.bean.UserAssessDetail;
import com.nju.software.assessment.bean.UserAssessRecord;

import java.util.Date;

public class DisTributeModel extends UserAssessDetail{
    //private Integer isDepart;
    //private
    private String dmbh;//部门编号

    private String yhbh;//用户编号

    private Long objectId;

    private String objectName;

    public Date getDistributeTime() {
        return distributeTime;
    }

    public void setDistributeTime(Date distributeTime) {
        this.distributeTime = distributeTime;
    }

    private Date distributeTime;



    public String getDmbh() {
        return dmbh;
    }

    public void setDmbh(String dmbh) {
        this.dmbh = dmbh;
    }

    public String getYhbh() {
        return yhbh;
    }

    public void setYhbh(String yhbh) {
        this.yhbh = yhbh;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }


}
