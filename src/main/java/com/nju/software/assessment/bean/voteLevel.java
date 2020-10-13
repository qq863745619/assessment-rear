package com.nju.software.assessment.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "voteLevel")
public class voteLevel {
    @Id
    private String type;

    //部门的名称
    private String name;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "voteLevel{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public voteLevel(String type, String name) {
        this.type = type;
        this.name = name;
    }
}
