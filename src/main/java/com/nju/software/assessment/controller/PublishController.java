package com.nju.software.assessment.controller;

import com.nju.software.assessment.bean.Publish;
import com.nju.software.assessment.bean.Reform;
import com.nju.software.assessment.dao.PublishDao;
import com.nju.software.assessment.dao.ReformDao;
import com.nju.software.assessment.dao2.YhbDao;
import com.nju.software.assessment.util.DateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.*;

@Controller
public class PublishController {

    @Resource
    private PublishDao publishDao;

    @Resource
    private YhbDao yhbDao;
    @Resource
    private ReformDao reformDao;

    @ResponseBody
    @RequestMapping(value = "/publish",method = RequestMethod.POST)
    @Transactional(transactionManager="platformTransactionManagerOne",rollbackFor = Exception.class)
    public Map publish(Publish publish) throws ParseException {
        int code=200;
        try{
            publish.setCreateTime(DateUtil.formatStr(publish.getCreateTime()));
            publish.setEndTime(DateUtil.formatStr(publish.getEndTime()));
            publish.setStartTime(DateUtil.formatStr(publish.getStartTime()));
            String id = publishDao.save(publish).getId();
            String [] names = publish.getPeoplename();
            for(String name:names){
                Integer userid = yhbDao.getIdByName(name);
                reformDao.save(new Reform(id,userid,0,"",null));
            }
        }catch (Exception e){
            code=-1;
        }

        Map<String,Object> map =new HashMap<>();
        map.put("code",code);
        return map;
    }
    @ResponseBody
    @RequestMapping(value = "/getAllPublish",method = RequestMethod.GET)
    public Map getAllPublish() {
        int code = 200;
        List<Publish> list = new ArrayList<>();
        Map map = new HashMap();
        try{
            list =publishDao.findAll();

        }catch (Exception e){
            code = -1;
        }
        map.put("data",list);
        map.put("code",code);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/getAllWeitou",method = RequestMethod.POST)
    public Map getAllWeitou(String name){
        int code =200;
        Map map = new HashMap();
        try{
            Integer userId = yhbDao.getIdByName(name);
            List<Reform> reforms = reformDao.findByUserIdAndIsReform(userId,0);
            List<Publish> publishes =new ArrayList<>();
            for(Reform reform:reforms){
                Publish publish = publishDao.findFormById(reform.getPublishId());
                publishes.add(publish);
            }
            map.put("data",publishes);
        }catch (Exception e){
            code = -1;

        }

        map.put("code",code);
        return map;

    }

    @ResponseBody
    @RequestMapping(value = "/getAllYitou",method = RequestMethod.POST)
    public Map getAllYitou(String name){
        int code =200;
        Map map = new HashMap();
        List<String> tpTime = new ArrayList<>();
        try{
            Integer userId = yhbDao.getIdByName(name);
            List<Reform> reforms = reformDao.findByUserIdAndIsReform(userId,1);
            List<Publish> publishes =new ArrayList<>();
            for(Reform reform:reforms){
                Publish publish = publishDao.findFormById(reform.getPublishId());
                publishes.add(publish);
                tpTime.add(String.valueOf(reform.getReformTime()));
            }
            map.put("data",publishes);
            map.put("tpTime",tpTime);
        }catch (Exception e){
            code = -1;

        }

        map.put("code",code);
        return map;

    }

    @ResponseBody
    @RequestMapping(value = "/getMyPublish",method = RequestMethod.POST)
    public Map getMypublish(String publisher) {
        int code = 200;
        List<Publish> list = new ArrayList<>();
        Map map = new HashMap();
        try{
            list =publishDao.findByPublisherOrderByCreateTimeDesc(publisher);

        }catch (Exception e){
            code = -1;
        }
        int num=0;
        List<Double> percentage = new ArrayList<>();
        for(Publish publish:list){
            List<Reform> reforms =reformDao.findByPublishId(publish.getId());
            int count = 0;
            for(Reform reform:reforms){
                if(reform.getIsReform()==1)
                    count+=1;
            }
            percentage.add(count/ (double) reforms.size());
        }
        map.put("data",list);
        map.put("percentage",percentage);
        map.put("code",code);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/deletePublishBatch",method = RequestMethod.POST)
    public Map deletePublishBatch(String ids){
        int code = 200;
        Map map = new HashMap();
        String []idsArr =  ids.split(",");
        try{
            for(String id:idsArr){
                publishDao.deleteById(id);
                List<Reform> reforms = reformDao.findByPublishId(id);
                for(Reform reform:reforms){
                    reformDao.delete(reform);
                }
            }
        }catch (Exception e){
            code = -1;
        }
        map.put("code",code);
        return map;


    }

    @ResponseBody
    @RequestMapping(value = "/finishPublishBatch",method = RequestMethod.POST)
    public Map finishPublishBatch(String ids){
        int code = 200;
        Map map = new HashMap();
        String []idsArr =  ids.split(",");
        try{
            for(String id:idsArr){
                Publish publish= publishDao.findById(id).get();
                if(DateUtil.StringToDate(publish.getEndTime()).getTime()<new Date().getTime()){
                    continue;
                }
                publish.setEndTime(DateUtil.dateToStrLong(new Date()));
                publishDao.save(publish);

            }
        }catch (Exception e){
            code = -1;
        }
        map.put("code",code);
        return map;


    }
}
