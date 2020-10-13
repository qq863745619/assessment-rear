package com.nju.software.assessment.controller;

import com.nju.software.assessment.bean2.DMB;
import com.nju.software.assessment.service.DmbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DmbController {

    @Autowired
    DmbService dmbService;

    //获取全部部门信息
    @ResponseBody
    @RequestMapping(value = "/getdmb")
    public Map<String,Object> findAll(){
       List<DMB> dmblist = dmbService.findAll();
       Map<String,Object> sMap = new HashMap<>();
       sMap.put("list",dmblist);

       return sMap;
    }
}
