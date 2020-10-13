package com.nju.software.assessment.model;

import com.nju.software.assessment.bean.UserAssessDetail;

public class UadExtModel extends UserAssessDetail {

    public UadExtModel(UserAssessDetail userAssessDetail,String objectName,String productName){

        super(userAssessDetail);

        this.productName = objectName;

        this.departMent = productName;

    }

    private String objectName;

    private String productName;

    public UadExtModel(UserAssessDetail userAssessDetail, String productNameByProductId, String departNameByUadId, String objectNameByProductId) {

        super(userAssessDetail);

        this.productName = productNameByProductId;

        this.departMent = departNameByUadId;

        this.objectName = objectNameByProductId;

    }

    public String getDepartMent() {

        return departMent;

    }

    public void setDepartMent(String departMent) {

        this.departMent = departMent;

    }

    private String departMent;

    public UadExtModel(UserAssessDetail userAssessDetail, String productNameByProductId) {

        super(userAssessDetail);

        this.productName = productNameByProductId;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
