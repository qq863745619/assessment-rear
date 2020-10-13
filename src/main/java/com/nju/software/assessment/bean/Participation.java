package com.nju.software.assessment.bean;

import javax.persistence.Entity;

//@Entity(name="toupiao_user")
public class Participation {

    private Integer userId;

    private Integer toupiao_id;
    //0表示未填写，1表示填写
    private Integer is_write;

    private String write_date;

    private String content;


}
