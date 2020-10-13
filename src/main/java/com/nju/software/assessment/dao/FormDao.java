package com.nju.software.assessment.dao;

import com.nju.software.assessment.bean.Form;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FormDao extends MongoRepository<Form,Integer> {
    Form findFormById(Integer id);
    void deleteFormById(Integer id);

}
