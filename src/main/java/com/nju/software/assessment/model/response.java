package com.nju.software.assessment.model;

public class response {
    private int code;
    private Object data;

    public response(int code, retInfo data) {
        this.code = code;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public response() {
    }

    @Override
    public String toString() {
        return "response{" +
                "code=" + code +
                ", data=" + data +
                '}';
    }
}
