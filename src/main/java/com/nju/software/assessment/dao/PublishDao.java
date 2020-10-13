package com.nju.software.assessment.dao;

import com.nju.software.assessment.bean.Publish;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface PublishDao extends MongoRepository<Publish,String> {
    List<Publish> findByPublisherOrderByCreateTimeDesc(String publisher);
    List<Publish> findByPublisherAndCreateTimeBetween(String name,String start,String end);
    Optional<Publish> findById(String id);
    Publish findFormById(String id);
    void deleteFormById(Integer id);
    void deleteById(String id);
}
