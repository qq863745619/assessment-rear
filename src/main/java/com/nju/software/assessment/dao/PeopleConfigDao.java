package com.nju.software.assessment.dao;

import com.nju.software.assessment.bean.PeopleConfig;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PeopleConfigDao extends MongoRepository<PeopleConfig,String> {
     public List<PeopleConfig> findAll();
     public void deleteById(String id);
}
