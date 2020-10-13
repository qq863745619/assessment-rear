package com.nju.software.assessment.dao;

import com.nju.software.assessment.bean.Static_Publish;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface Static_PublishDao extends MongoRepository<Static_Publish,String> {
    public Static_Publish findAllById(String id);
    public List<Static_Publish> findByPublisherOrderByCreateDateDesc(String publisher);
    public List<Static_Publish> findByTitleLike(String pattern);
    public List<Static_Publish> findByPublisherAndCreateDateBetween(String name,String start,String end);
    public void deleteById(String id);
}
