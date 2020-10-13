package com.nju.software.assessment.controller;

import com.mongodb.BasicDBObject;
import com.nju.software.assessment.bean.Static_Publish;
import com.nju.software.assessment.bean.Static_Reform;
import com.nju.software.assessment.dao.StaticDao;
import com.nju.software.assessment.dao.Static_ReformDao;
import com.nju.software.assessment.query.staticPublishCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class Static_ReformController {

    @Resource
    private Static_ReformDao static_reformDao;
    @Resource
    private StaticDao staticDao;

    @ResponseBody
    @RequestMapping(value = "/getAllStaticYitou",method = RequestMethod.POST)
    public Map getAllStaticYitou(Integer pageNum, String name){
        int code =200;
        Map map = new HashMap();
        List<String> date = new ArrayList<>();
        try{
            Page<Static_Publish> static_publishes= staticDao.findAllPublishByNameAndIsReformAndIsShow(pageNum,name,"yes","yes");
            for(Static_Publish static_publish:static_publishes){
                Static_Reform static_reform = static_reformDao.findByStaticPublishIdAndName(static_publish.getId(),name).get(0);
                date.add(static_reform.getDate());
            }
            map.put("data",static_publishes.getContent());
            map.put("totalPages",static_publishes.getTotalPages());
            map.put("date",date);
        }catch (Exception e){
            code = -1;

        }

        map.put("code",code);
        return map;

    }
    @ResponseBody
    @RequestMapping(value = "/static_abandon",method = RequestMethod.POST)
    public Map static_abandon(String id,String name){
        int code =200;
        Map map = new HashMap();
        try{
            List<Static_Reform >reforms = static_reformDao.findByStaticPublishIdAndName(id,name);
            if(reforms.size()>0){
                Static_Reform Reform = reforms.get(0);
                Reform.setIsReform("yes");
                static_reformDao.save(Reform);

            }else code=-1;
        }catch (Exception e){
            code=-1;
        }
        map.put("code",code);
        return map;

    }

    @ResponseBody
    @RequestMapping(value = "/getMyStaticPublish",method = RequestMethod.POST)
    public Map getMyStaticPublish(Integer pageNum,String publisher){
        int code =200;
        Map map = new HashMap();
        try{

            Page<Static_Publish> publishes = staticDao.findAllByPublisher(pageNum,publisher);
//            List<Static_Publish> pu.blishes = static_publishDao.findByPublisherOrderByCreateDateDesc(publisher);
            List<Double> percentage = new ArrayList<>();
            for(Static_Publish publish:publishes){
                List<Static_Reform> reforms = static_reformDao.findByStaticPublishId(publish.getId());
                int count =0;
                for(Static_Reform reform:reforms){
                    if(reform.getIsReform().equals("yes"))
                        count++;
                }
                percentage.add(count/ (double) reforms.size());
            }
            map.put("totalPages",publishes.getTotalPages());
            map.put("data",publishes.getContent());
            map.put("percentage",percentage);

        }catch (Exception e){
            code=-1;
        }
        map.put("code",code);
        return map;

    }

    @ResponseBody
    @RequestMapping(value = "/getMyStaticPublishConditionly",method = RequestMethod.POST)
    public Map getMyStaticPublishConditionly(staticPublishCondition condition){
        int code =200;
        Map map = new HashMap();
        try{
            Page<Static_Publish> publishes = staticDao.findConditionlyByPublisher(condition);
//            List<Static_Publish> publishes = static_publishDao.findByPublisherOrderByCreateDateDesc(publisher);
            List<Double> percentage = new ArrayList<>();
            List<String> date=new ArrayList<>();
            for(Static_Publish publish:publishes){
                List<Static_Reform> reforms = static_reformDao.findByStaticPublishId(publish.getId());
                int count =0;
                for(Static_Reform reform:reforms){
                    if(reform.getIsReform().equals("yes"))
                        count++;
                    if(reform.getName().equals(condition.getName())){
                        date.add(reform.getDate());
                    }
                }
                percentage.add(count/ (double) reforms.size());
            }
            map.put("totalPages",publishes.getTotalPages());
            map.put("data",publishes.getContent());
            map.put("percentage",percentage);
            map.put("date",date);
        }catch (Exception e){
            code=-1;
        }
        map.put("code",code);
        return map;

    }

    @ResponseBody
    @RequestMapping(value = "/getConditionly",method = RequestMethod.POST)
    public Map getConditionly(staticPublishCondition condition){
        int code =200;
        Map map = new HashMap();
        try{
            AggregationResults<BasicDBObject> aggregationResults = staticDao.getConditionly(condition);

            Page<Static_Publish> publishes = staticDao.findConditionlyByPublisher(condition);
//            List<Static_Publish> publishes = static_publishDao.findByPublisherOrderByCreateDateDesc(publisher);
            List<Double> percentage = new ArrayList<>();
            List<String> date=new ArrayList<>();
            for(Static_Publish publish:publishes){
                List<Static_Reform> reforms = static_reformDao.findByStaticPublishId(publish.getId());
                int count =0;
                for(Static_Reform reform:reforms){
                    if(reform.getIsReform().equals("yes"))
                        count++;
                    if(reform.getName()==condition.getPublisher()){
                        date.add(reform.getDate());
                    }
                }
                percentage.add(count/ (double) reforms.size());
            }
            map.put("totalPages",publishes.getTotalPages());
            map.put("data",publishes.getContent());
            map.put("percentage",percentage);
            map.put("date",date);
        }catch (Exception e){
            code=-1;
        }
        map.put("code",code);
        return map;

    }

    @ResponseBody
    @RequestMapping(value = "/getAllStaticWeitou",method = RequestMethod.POST)
    public Map getAllStaticWeitou(Integer pageNum,String name){
        int code =200;
        Map map = new HashMap();
        try{
            Page<Static_Publish> static_publishes = staticDao.findAllPublishByNameAndIsReformAndIsShow(pageNum,name,"no","yes");
            map.put("totalPages",static_publishes.getTotalPages());
            map.put("data",static_publishes.getContent());
        }catch (Exception e){
            code = -1;

        }
        map.put("code",code);
        return map;

    }
}
