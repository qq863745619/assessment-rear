package com.nju.software.assessment.model;

import java.util.List;

public class DistributionListModel {
    private List<List<String>> yhbhList;
    private List<String>  keyList;

    public DistributionListModel() {
    }

    @Override
    public String toString() {
        return "DistributionListModel{" +
                "yhbhList=" + yhbhList +
                ", keyList=" + keyList +
                ", valueList=" + valueList +
                ", amountList=" + amountList +
                '}';
    }

    public DistributionListModel(List<List<String>> yhbhList, List<String> keyList, List<String> valueList, List<String> amountList) {
        this.yhbhList = yhbhList;
        this.keyList = keyList;
        this.valueList = valueList;
        this.amountList = amountList;
    }

    private List<String>  valueList;
    private List<String>  amountList;

    public List<List<String>> getYhbhList() {
        return yhbhList;
    }

    public void setYhbhList(List<List<String>> yhbhList) {
        this.yhbhList = yhbhList;
    }

    public List<String> getKeyList() {
        return keyList;
    }

    public void setKeyList(List<String> keyList) {
        this.keyList = keyList;
    }

    public List<String> getValueList() {
        return valueList;
    }

    public void setValueList(List<String> valueList) {
        this.valueList = valueList;
    }

    public List<String> getAmountList() {
        return amountList;
    }

    public void setAmountList(List<String> amountList) {
        this.amountList = amountList;
    }
}
