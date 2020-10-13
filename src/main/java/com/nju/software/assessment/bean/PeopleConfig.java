package com.nju.software.assessment.bean;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

@Document
public class PeopleConfig {
    @Id
    private String id;
    private String name;
    private List<List<String>> peopleList;

    public PeopleConfig(String name, List<List<String>> peopleList) {
        this.name = name;
        this.peopleList = peopleList;
    }

    public PeopleConfig() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<List<String>> getPeopleList() {
        return peopleList;
    }

    public void setPeopleList(List<List<String>> peopleList) {
        this.peopleList = peopleList;
    }
}
