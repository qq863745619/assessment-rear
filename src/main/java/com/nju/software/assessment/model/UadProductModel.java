package com.nju.software.assessment.model;

import com.nju.software.assessment.bean.Product;
import com.nju.software.assessment.bean.UserAssessDetail;
import com.nju.software.assessment.bean.UserAssessRecord;
import com.nju.software.assessment.bean2.YHB;

import java.util.Date;

public class UadProductModel {

    private Long userAssessDetailId;

    private Long userAssessRecordId;

    private Long productId;

    private String productName;

    private String addressId;

    private Long objectId;

    private String productParameter;

    private Long productAddressId;

    private String productContains;

    @Override
    public String toString() {
        return "UadProductModel{" +
                "userAssessDetailId=" + userAssessDetailId +
                ", userAssessRecordId=" + userAssessRecordId +
                ", productId=" + productId +
                ", productName='" + productName + '\'' +
                ", addressId=" + addressId +
                ", objectId=" + objectId +
                ", productParameter='" + productParameter + '\'' +
                ", productAddressId=" + productAddressId +
                ", productContains='" + productContains + '\'' +
                ", yhmc='" + yhmc + '\'' +
                ", productAddTime=" + productAddTime +
                ", uniqueIdentify='" + uniqueIdentify + '\'' +
                ", responseYhbh=" + responseYhbh +
                ", isOut=" + isOut +
                '}';
    }

    public String getYhmc() {
        return yhmc;
    }

    public void setYhmc(String yhmc) {
        this.yhmc = yhmc;
    }

    private String yhmc;

    public UadProductModel(Long userAssessDetailId, Long userAssessRecordId, Long productId, String productName, String addressId, Long objectId, String productParameter, Long productAddressId, String productContains, Date productAddTime, String uniqueIdentify, Integer responseYhbh, Integer isOut) {
        this.userAssessDetailId = userAssessDetailId;
        this.userAssessRecordId = userAssessRecordId;
        this.productId = productId;
        this.productName = productName;
        this.addressId = addressId;
        this.objectId = objectId;
        this.productParameter = productParameter;
        this.productAddressId = productAddressId;
        this.productContains = productContains;
        this.productAddTime = productAddTime;
        this.uniqueIdentify = uniqueIdentify;
        this.responseYhbh = responseYhbh;
        this.isOut = isOut;

    }

    public UadProductModel(UserAssessDetail record, Product product, YHB yhb) {
        this.userAssessDetailId = record.getUserAssessDetailId();
        this.userAssessRecordId = record.getUserAssessRecordId();
        this.productId = record.getProductId();
        this.addressId = record.getAddressId();
        this.objectId = product.getObjectId();
        this.productParameter = product.getProductParameter();
        this.productAddressId = product.getProductAddressId();
        this.productContains = product.getProductContains();
        this.productAddTime = product.getProductAddTime();
        this.productName = product.getProductName();
        this.uniqueIdentify = record.getUniqueIdentify();
        this.responseYhbh = record.getResponseYhbh();
        this.isOut = record.getIsOut();
        this.yhmc = yhb.getYhmc();
    }

    private Date productAddTime;


    public String getUniqueIdentify() {
        return uniqueIdentify;
    }

    public void setUniqueIdentify(String uniqueIdentify) {
        this.uniqueIdentify = uniqueIdentify;
    }

    public Integer getResponseYhbh() {
        return responseYhbh;
    }

    public void setResponseYhbh(Integer responseYhbh) {
        this.responseYhbh = responseYhbh;
    }

    public Integer getIsOut() {
        return isOut;
    }

    public void setIsOut(Integer isOut) {
        this.isOut = isOut;
    }

    private  String uniqueIdentify;

    private Integer responseYhbh;

    private Integer isOut;

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }



    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public UadProductModel(UserAssessDetail record, Product product){

        this.userAssessDetailId = record.getUserAssessDetailId();
        this.userAssessRecordId = record.getUserAssessRecordId();
        this.productId = record.getProductId();
        this.addressId = record.getAddressId();
        this.objectId = product.getObjectId();
        this.productParameter = product.getProductParameter();
        this.productAddressId = product.getProductAddressId();
        this.productContains = product.getProductContains();
        this.productAddTime = product.getProductAddTime();
        this.productName = product.getProductName();
        this.uniqueIdentify = record.getUniqueIdentify();
        this.responseYhbh = record.getResponseYhbh();
        this.isOut = record.getIsOut();
    }


    public Long getUserAssessDetailId() {
        return userAssessDetailId;
    }

    public void setUserAssessDetailId(Long userAssessDetailId) {
        this.userAssessDetailId = userAssessDetailId;
    }

    public Long getUserAssessRecordId() {
        return userAssessRecordId;
    }

    public void setUserAssessRecordId(Long userAssessRecordId) {
        this.userAssessRecordId = userAssessRecordId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }



    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public String getProductParameter() {
        return productParameter;
    }

    public void setProductParameter(String productParameter) {
        this.productParameter = productParameter;
    }

    public Long getProductAddressId() {
        return productAddressId;
    }

    public void setProductAddressId(Long productAddressId) {
        this.productAddressId = productAddressId;
    }

    public String getProductContains() {
        return productContains;
    }

    public void setProductContains(String productContains) {
        this.productContains = productContains;
    }

    public Date getProductAddTime() {
        return productAddTime;
    }

    public void setProductAddTime(Date productAddTime) {
        this.productAddTime = productAddTime;
    }


}
