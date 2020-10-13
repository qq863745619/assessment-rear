package com.nju.software.assessment.dao;

import com.nju.software.assessment.bean.Static_Reform;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface Static_ReformDao extends MongoRepository<Static_Reform,Integer> {
    public List<Static_Reform> findByIsReformAndNameOrderByCreateDateDesc(String isReform,String name);
    public List<Static_Reform> findByIsReformAndNameAndShow(String isReform,String name,String show);
    public List<Static_Reform> findByStaticPublishIdAndName(String id,String name);
    public List<Static_Reform> findByStaticPublishId(String id);
    public List<Static_Reform> findByStaticPublishIdAndIsReform(String id,String isReform);
    public void deleteAllByStaticPublishId(String id);
    public List<Static_Reform> findByNameAndDateBetween(String name,String start,String end);
}
