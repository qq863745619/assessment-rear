package com.nju.software.assessment.model;

public class errorInfo {
    private String msg;
    private String status;

    public errorInfo(String msg, String status) {
        this.msg = msg;
        this.status = status;
    }

    public errorInfo() {
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
