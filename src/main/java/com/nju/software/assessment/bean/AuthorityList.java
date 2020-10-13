package com.nju.software.assessment.bean;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class AuthorityList {

    private List<String> value;

    private List<List<String>>  peopleList;
    public List<String> getValue() {
        return value;
    }

    public void setValue(List<String> value) {
        this.value = value;
    }

    public List<List<String>> getPeopleList() {
        return peopleList;
    }

    public void setPeopleList(List<List<String>> peopleList) {
        this.peopleList = peopleList;
    }


    public AuthorityList(List<String> value,List<List<String>> peopleList)
    {
        this.value = value;
        this.peopleList = peopleList;
    }
}
