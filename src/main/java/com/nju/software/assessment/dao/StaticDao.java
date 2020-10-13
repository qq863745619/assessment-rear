package com.nju.software.assessment.dao;

import com.mongodb.BasicDBObject;
import com.nju.software.assessment.bean.Static_Publish;
import com.nju.software.assessment.query.staticPublishCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;

public interface StaticDao {
    public  AggregationResults<BasicDBObject> getConditionly(staticPublishCondition condition);
    public Page<Static_Publish> findAllByPublisher(Integer pageNum, String publisher);

    public Page<Static_Publish> findConditionlyByPublisher(staticPublishCondition condition);

    public Page<Static_Publish> findAllByName(Integer pageNum, String name);

    public Page<Static_Publish> findAllPublishByNameAndIsReformAndIsShow(Integer pageNum, String name, String isReform, String isShow);
}
