package com.nju.software.assessment.dao;
import com.mongodb.BasicDBObject;
import com.nju.software.assessment.bean.Static_Publish;
import com.nju.software.assessment.bean.Static_Reform;
import com.nju.software.assessment.query.MongoPageable;
import com.nju.software.assessment.query.staticPublishCondition;
import com.nju.software.assessment.util.DateUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import com.nju.software.assessment.util.DateUtil;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class StaticDaoImpl implements StaticDao{
    @Resource
    private  MongoTemplate mongoTemplate;
    @Resource
    private Static_PublishDao static_publishDao;
    public  AggregationResults<BasicDBObject> getConditionly(staticPublishCondition condition){
        Criteria criteria = new Criteria();
        Integer startRows = condition.getPageNum()* condition.getPageSize();
        if(condition.getPublisher()!=null&&!condition.getPublisher().trim().equals("")){
            criteria.and("publisher").is(condition.getPublisher());
        }
        if(condition.getTitle()!=null&&!condition.getTitle().trim().equals("")){
            Pattern pattern = Pattern.compile("^.*"+condition.getTitle()+".*$", Pattern.CASE_INSENSITIVE);
            criteria.and("title").regex(pattern);
        }
        if(condition.getLevels()!=null&&condition.getLevels().size()>0){
            criteria.and("level").in(condition.getLevels());

        }
        if(condition.getIsReform()!=null&&!condition.getIsReform().trim().equals("")){
            criteria.and("reform.isReform").is(condition.getIsReform());
        }
        if(condition.getIsShow()!=null&&!condition.getIsShow().trim().equals("")){
            criteria.and("reform.show").is(condition.getIsShow());
        }
        if(condition.getPublishdateRange()!=null&&condition.getPublishdateRange().size()>0){
            criteria.and("createDate").gte(condition.getPublishdateRange().get(0)).lte(condition.getPublishdateRange().get(1));

        }
        if(condition.getStartdateRange()!=null&&condition.getStartdateRange().size()>0){
            criteria.and("startDate").gte(condition.getStartdateRange().get(0)).lte(condition.getStartdateRange().get(1));
        }
        if(condition.getEnddateRange()!=null&&condition.getEnddateRange().size()>0){
            criteria.and("endDate").gte(condition.getEnddateRange().get(0)).lte(condition.getEnddateRange().get(1));

        }
        if(condition.getTpdateRange()!=null&&condition.getTpdateRange().size()>0){
            criteria.and("date").gte(condition.getTpdateRange().get(0)).lte(condition.getTpdateRange().get(1));

        }
        if(condition.getTpRatio()!=null&&condition.getTpRatio().size()>0){
            criteria.and("ratio").gte(condition.getTpRatio().get(0)).lte(condition.getTpRatio().get(1));

        }
        if(condition.getQzRatio()!=null&&condition.getQzRatio().size()>0){
            criteria.and("value").gte(condition.getQzRatio().get(0)).lte(condition.getQzRatio().get(1));
        }
        if(condition.getStatus()!=null&&condition.getStatus().size()>0&&condition.getStatus().size()<3){
            if(condition.getStatus().size()==1){
                if(condition.getStatus().get(0).equals("未开始")){

                    criteria.and("startDate").gt(DateUtil.dateToStrLong(new Date()));

                }else if(condition.getStatus().get(0).equals("进行中")){

                    criteria.and("startDate").lte(DateUtil.dateToStrLong(new Date())).and("endDate").gte(DateUtil.dateToStrLong(new Date()));

                }else {

                    criteria.and("endDate").lt(DateUtil.dateToStrLong(new Date()));

                }
            }
            else {
                if(condition.getStatus().get(0).equals("未开始")&&condition.getStatus().get(1).equals("进行中")){

                    criteria.and("endDate").gte(DateUtil.dateToStrLong(new Date()));

                }else if(condition.getStatus().get(0).equals("未开始")&&condition.getStatus().get(1).equals("已结束")){

                    criteria.and("level").gte(0).orOperator(Criteria.where("startDate").gt(DateUtil.dateToStrLong(new Date())),Criteria.where("endDate").lt(DateUtil.dateToStrLong(new Date())));
                }else {

                    criteria.and("startDate").lte(DateUtil.dateToStrLong(new Date()));

                }
            }
        }
        Aggregation newAggregation = Aggregation.newAggregation(
                Aggregation.lookup("static_Reform","_id","staticPublishId","reform"),//从表名，主表联接字段，从表联接字段，别名
                Aggregation.match(criteria),
                Aggregation.skip(startRows),
                Aggregation.limit(condition.getPageSize()),
                Aggregation.sort(new Sort(Sort.Direction.DESC, "createDate"))
        );
        AggregationResults<BasicDBObject> aggregate = mongoTemplate.aggregate(
                newAggregation ,"static_Publish",BasicDBObject.class//A表，是查询的主表
        );
        return aggregate;

    }
    public Page<Static_Publish> findAllByPublisher(Integer pageNum,String publisher) {
        Query query = new Query();
        query.addCriteria(new Criteria().and("publisher").is(publisher));
        Sort sort = new Sort(Sort.Direction.DESC, "createDate");
        //当前页码，每页条数，排序方式
        MongoPageable pageable = new MongoPageable(pageNum,10,sort);
        // 查询出一共的条数0
        Long count = mongoTemplate.count(query, Static_Publish.class);
        // 查询
        List<Static_Publish> list = mongoTemplate.find(query.with(pageable), Static_Publish.class);
        // 将集合与分页结果封装
        Page<Static_Publish> pagelist = new PageImpl<>(list, pageable, count);
        return pagelist;
    }
    public Page<Static_Publish> findAllByName(Integer pageNum,String name) {
        Query query = new Query();
        query.addCriteria(Criteria.where("cp_namelist").in(name));
        Sort sort = new Sort(Sort.Direction.DESC, "createDate");
        //当前页码，每页条数，排序方式
        MongoPageable pageable = new MongoPageable(pageNum,10,sort);
        // 查询出一共的条数
        Long count = mongoTemplate.count(query, Static_Publish.class);
        // 查询
        List<Static_Publish> list = mongoTemplate.find(query.with(pageable), Static_Publish.class);
        // 将集合与分页结果封装
        Page<Static_Publish> pagelist = new PageImpl<>(list, pageable, count);
        return pagelist;
    }
    public Page<Static_Publish> findAllPublishByNameAndIsReformAndIsShow(Integer pageNum,String name,String isReform,String isShow){
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));
        query.addCriteria(Criteria.where("isReform").is(isReform));
        query.addCriteria(Criteria.where("show").is(isShow));
        Sort sort = new Sort(Sort.Direction.DESC, "createDate");
        //当前页码，每页条数，排序方式
        MongoPageable pageable = new MongoPageable(pageNum,10,sort);
        // 查询出一共的条数
        Long count = mongoTemplate.count(query, Static_Reform.class);
        // 查询
        List<Static_Reform> list = mongoTemplate.find(query.with(pageable), Static_Reform.class);
        // 将集合与分页结果封装
        List<Static_Publish> static_publishes = new ArrayList<>();
        for(Static_Reform static_reform:list){
            String publishId = static_reform.getStaticPublishId();
            Static_Publish static_publish =static_publishDao.findAllById(publishId);
            static_publishes.add(static_publish);
        }
        Page<Static_Publish> pagelist = new PageImpl<>(static_publishes, pageable, count);
        return pagelist;
    }
    public Page<Static_Publish> findConditionlyByPublisher(staticPublishCondition condition) {
        Query query = new Query();
        if(condition.getName()!=null&&!condition.getName().trim().equals("")){
            query.addCriteria(
                    Criteria.where("name").is(condition.getName())
            );
        }
        if(condition.getPublisher()!=null&&!condition.getPublisher().trim().equals("")){
            Pattern pattern = Pattern.compile("^.*"+condition.getPublisher()+".*$", Pattern.CASE_INSENSITIVE);
            query.addCriteria(
                    Criteria.where("publisher").regex(pattern)
            );
        }
        if(condition.getTitle()!=null&&!condition.getTitle().trim().equals("")){
            Pattern pattern = Pattern.compile("^.*"+condition.getTitle()+".*$", Pattern.CASE_INSENSITIVE);
            query.addCriteria(
                    Criteria.where("title").regex(pattern)
            );
        }
        if(condition.getIsReform()!=null&&!condition.getIsReform().trim().equals("")){
            query.addCriteria(
                    Criteria.where("isReform").is(condition.getIsReform())
            );
        }
        if(condition.getIsShow()!=null&&!condition.getIsShow().trim().equals("")){
            query.addCriteria(
                    Criteria.where("show").is(condition.getIsShow())
            );
        }
        if(condition.getLevels()!=null&&condition.getLevels().size()>0){
            query.addCriteria(
                    Criteria.where("level").in(condition.getLevels())
            );
        }
        if(condition.getPublishdateRange()!=null&&condition.getPublishdateRange().size()>0){
            query.addCriteria(
                    Criteria.where("createDate").gte(condition.getPublishdateRange().get(0)).lte(condition.getPublishdateRange().get(1))
            );

        }
        if(condition.getStartdateRange()!=null&&condition.getStartdateRange().size()>0){
            query.addCriteria(
                    Criteria.where("startDate").gte(condition.getStartdateRange().get(0)).lte(condition.getStartdateRange().get(1))

            );
        }
        if(condition.getEnddateRange()!=null&&condition.getEnddateRange().size()>0){
            query.addCriteria(
                    Criteria.where("endDate").gte(condition.getEnddateRange().get(0)).lte(condition.getEnddateRange().get(1))

            );
        }
        if(condition.getTpRatio()!=null&&condition.getTpRatio().size()>0){
            query.addCriteria(
                    Criteria.where("ratio").gte(condition.getTpRatio().get(0)).lte(condition.getTpRatio().get(1))
            );
        }
        if(condition.getQzRatio()!=null&&condition.getQzRatio().size()>0){
            query.addCriteria(
                    Criteria.where("value").gte(condition.getQzRatio().get(0)).lte(condition.getQzRatio().get(1))
            );
        }
        if(condition.getStatus()!=null&&condition.getStatus().size()>0&&condition.getStatus().size()<3){
            if(condition.getStatus().size()==1){
                if(condition.getStatus().get(0).equals("未开始")){
                    query.addCriteria(
                            Criteria.where("startDate").gt(DateUtil.dateToStrLong(new Date()))
                    );
                }else if(condition.getStatus().get(0).equals("进行中")){
                    query.addCriteria(
                            Criteria.where("startDate").lte(DateUtil.dateToStrLong(new Date())).and("endDate").gte(DateUtil.dateToStrLong(new Date()))
                    );
                }else {
                    query.addCriteria(
                            Criteria.where("endDate").lt(DateUtil.dateToStrLong(new Date()))
                    );
                }
            }
            else {
                if(condition.getStatus().get(0).equals("未开始")&&condition.getStatus().get(1).equals("进行中")){
                    query.addCriteria(
                            Criteria.where("endDate").gte(DateUtil.dateToStrLong(new Date()))
                    );
                }else if(condition.getStatus().get(0).equals("未开始")&&condition.getStatus().get(1).equals("已结束")){
                    query.addCriteria(
                            Criteria.where("level").gte(0).orOperator(Criteria.where("startDate").gt(DateUtil.dateToStrLong(new Date())),Criteria.where("endDate").lt(DateUtil.dateToStrLong(new Date())))
                    );
                }else {
                    query.addCriteria(
                            Criteria.where("startDate").lte(DateUtil.dateToStrLong(new Date()))
                    );
                }
            }
        }
        Sort sort = new Sort(Sort.Direction.DESC, "createDate");
        //当前页码，每页条数，排序方式
        MongoPageable pageable = new MongoPageable(condition.getPageNum(),10,sort);
        // 查询出一共的条数
        Long count = mongoTemplate.count(query, Static_Reform.class);
        // 查询
        List<Static_Reform> list = mongoTemplate.find(query.with(pageable), Static_Reform.class);
        // 将集合与分页结果封装
        List<Static_Publish> static_publishes = new ArrayList<>();
        for(Static_Reform static_reform:list){
            String publishId = static_reform.getStaticPublishId();
            Static_Publish static_publish =static_publishDao.findAllById(publishId);
            static_publishes.add(static_publish);
        }
        Page<Static_Publish> pagelist = new PageImpl<>(static_publishes, pageable, count);
        return pagelist;

    }


}
