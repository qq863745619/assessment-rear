package com.nju.software.assessment.bean;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;
import java.util.List;

@Entity(name = "toupiao")//发布的投票
public class TouPiao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //模板的id
    private Integer muban_id;
    //模板的类型
    private Integer type;

    //发布投票的级别
    private Integer level;

    //前置条件
    private Integer premises;

//    /***
//     * 1、关系维护端，负责多对多关系的绑定和解除
//     *     2、@JoinTable注解的name属性指定关联表的名字，joinColumns指定外键的名字，关联到关系维护端(TouPiao)
//     *     3、inverseJoinColumns指定外键的名字，要关联的关系被维护端(YHB)
//     *     4、其实可以不使用@JoinTable注解，默认生成的关联表名称为主表表名+下划线+从表表名，
//     *     即表名为toupiao_yhb
//     *     关联到主表的外键名：主表名+下划线+主表中的主键列名,即toupiao_id
//     *     关联到从表的外键名：主表中用于关联的属性名+下划线+从表的主键列名,即yhb_yhbh
//     *     主表就是关系维护端对应的表，从表就是关系被维护端对应的表
//     */
//    @ManyToMany
//    @JoinTable(name="toupiao_yhb",joinColumns = @JoinColumn(name = "toupiao_id"),inverseJoinColumns = @JoinColumn(name="yhb_yhbh"))
//    private List<YHB> userlist;

    public TouPiao() {
    }

    public TouPiao(Integer muban_id, Integer type, Integer level,  Integer premises) {
        this.muban_id = muban_id;
        this.type = type;
        this.level = level;
        this.premises = premises;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMuban_id() {
        return muban_id;
    }

    public void setMuban_id(Integer muban_id) {
        this.muban_id = muban_id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getPremises() {
        return premises;
    }

    public void setPremises(Integer premises) {
        this.premises = premises;
    }
}
