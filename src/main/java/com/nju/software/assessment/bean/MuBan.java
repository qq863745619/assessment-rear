package com.nju.software.assessment.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

//模板表 alter table muban modify column content text
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
@Entity(name = "muban")
public class MuBan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //模板所有者
    private String user;

    //模板标题
    private String title;

    //模板描述
    private String miaoshu;

    //模板内容
    private String content;

    //模板是否共享,1为共享，0为私有
    private int isshare;

    //模板创办时间
    private String createtime;

    //模板图片地址
    //图片
    //@Lob 通常与@Basic同时使用，提高访问速度
//    @Lob
//    @Basic(fetch = FetchType.LAZY)
//    @Column(name="imageurl", columnDefinition="longblob", nullable=true)
//    private byte[] file;

    private String dataUrl;

    //模板类型,1为富文本，0为自定义表单
    private int type;

}
