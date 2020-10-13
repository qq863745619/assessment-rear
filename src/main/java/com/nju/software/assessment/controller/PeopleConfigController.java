package com.nju.software.assessment.controller;


import com.nju.software.assessment.bean.PeopleConfig;
import com.nju.software.assessment.dao.PeopleConfigDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PeopleConfigController {

    @Resource
    private PeopleConfigDao peopleConfigDao;


    @ResponseBody
    @RequestMapping(value = "/getAllPeopleConfigs",method = RequestMethod.POST)
    public Map getAllPeopleConfigs(){
        int code = 200;
        Map map = new HashMap();
        try{
            List<PeopleConfig> peopleConfigs = peopleConfigDao.findAll();
            map.put("data",peopleConfigs);
        }catch (Exception e){
            code = -1;
        }
        map.put("code",code);
        return map;
    }
    @ResponseBody
    @RequestMapping(value = "/addPeopleConfig",method = RequestMethod.POST)
    public Map addPeopleConfig(PeopleConfig peopleConfig){
        int code = 200;
        Map map = new HashMap();
        try{
            peopleConfigDao.save(peopleConfig);
        }catch (Exception e){
            code = -1;
        }
        map.put("code",code);
        return map;
    }
    @ResponseBody
    @RequestMapping(value = "/deletePeopleConfig",method = RequestMethod.POST)
    public Map deletePeopleConfig(String id){
        int code = 200;
        Map map = new HashMap();
        try{
            peopleConfigDao.deleteById(id);
        }catch (Exception e){
            code = -1;
        }
        map.put("code",code);
        return map;
    }
}
