package com.nju.software.assessment.bean;

import javax.persistence.*;

@Entity
@Table(name = "Md")
public class Md {
    @Id
    private MdId id;

    public MdId getId() {
        return id;
    }

    public void setId(MdId id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Md{" +
                "id=" + id +
                '}';
    }

    public Md() {
    }
}
