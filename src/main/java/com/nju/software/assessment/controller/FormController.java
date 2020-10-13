package com.nju.software.assessment.controller;

import com.nju.software.assessment.bean.Form;
import com.nju.software.assessment.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@EnableAutoConfiguration
public class FormController {

    @Autowired
    FormService formService;


    @ResponseBody
    @RequestMapping(value = "/addform")
    public void addmuban( Form form){
        try{
           formService.save(form);
        }catch (Exception e){
            System.out.println(e);
        }

        System.out.println(form.toString());
    }


    @ResponseBody
    @RequestMapping(value = "/findform")
    public Map<String,Object> getMuBanByUserAndIsshare(Integer id){
        Form form = formService.findFormById(id);
        Map<String,Object> sMap = new HashMap<>();
        sMap.put("list",form);

        return sMap;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteform")
    public void deleteFormById(Integer id){
        formService.deleteFormById(id);

    }


}
