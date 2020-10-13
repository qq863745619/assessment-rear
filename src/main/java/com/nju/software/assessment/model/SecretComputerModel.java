package com.nju.software.assessment.model;

import java.util.List;

public class SecretComputerModel {
    List<String> bmList;

    Integer isSecret;

    Integer isComputer;


    public SecretComputerModel(List<String> bmList, Integer isSecret, Integer isComputer) {
        this.bmList = bmList;
        this.isSecret = isSecret;
        this.isComputer = isComputer;
    }


    public Integer getIsComputer() {
        return isComputer;
    }

    public void setIsComputer(Integer isComputer) {
        this.isComputer = isComputer;
    }


    public List<String> getBmList() {
        return bmList;
    }

    public void setBmList(List<String> bmList) {
        this.bmList = bmList;
    }

    public Integer getIsSecret() {
        return isSecret;
    }

    public void setIsSecret(Integer isSecret) {
        this.isSecret = isSecret;
    }


}
