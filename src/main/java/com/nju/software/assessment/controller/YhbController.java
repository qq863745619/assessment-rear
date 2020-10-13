package com.nju.software.assessment.controller;


import com.nju.software.assessment.bean2.DMB;
import com.nju.software.assessment.bean2.YHB;
import com.nju.software.assessment.model.*;
import com.nju.software.assessment.service.AuthorityService;
import com.nju.software.assessment.service.DmbService;
import com.nju.software.assessment.service.YhbService;
import com.nju.software.assessment.util.TokenProccessor;
import com.nju.software.assessment.util.TokenTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class YhbController {

    @Autowired
    YhbService yhbService;

    @Autowired
    AuthorityService authorityService;

    @Autowired
    DmbService dmbService;
    //后门，不需要验证码
    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public response login(@RequestBody user user, HttpServletRequest request){
        String username=user.getUsername();
        String password=user.getPassword();
       // Yh yh = yhbService.findByYhmc(username);
        YHB yhb = yhbService.findYHBByYhdm(username);
        DMB dmb = dmbService.findByBmdm(yhb.getYhbm());
        response r = new response();
        if(yhb==null) {
            r.setCode(-1);
            r.setData(new errorInfo("用户不存在", "fail"));
        }
        else {
            if (yhb.getYhkl().equals(password)) {

                List<Long> qxbh = authorityService.findAuthorityByYhbh(Long.valueOf(yhb.getYhbh()));



                String token = TokenProccessor.getInstance().makeToken();
                System.out.println(token);
                r.setCode(200);
                r.setData(new retInfo(token,
                        yhb.getYhmc(),
                        yhb.getYhsf()==null?"":yhb.getYhsf(),
                        dmb.getDmms(),
                        yhb.getYhbm(),
                        yhb.getYhbh(),
                        qxbh

                )
                );
                request.getSession().setAttribute(TokenTools.tokenServerkey,token);
                System.out.println( request.getSession().getAttribute("Token"));
            } else {
                r.setCode(-1);
                r.setData(new errorInfo("用户名错误", "fail"));
            }
        }
        return r;
    }

    @ResponseBody
    @RequestMapping(value = "/loginassess",method = RequestMethod.POST)
    public response loginassess(@RequestBody user user, HttpServletRequest request){
       String username=user.getUsername();
        String password=user.getPassword();
        Yh yh = yhbService.findByYhmc(username);

        response r = new response();
        if(yh==null) {
            r.setCode(-1);
            r.setData(new errorInfo("用户不存在", "fail"));
        }
        else {
            if (yh.getPassword().equals(password)) {




                String token = TokenProccessor.getInstance().makeToken();
                System.out.println(token);
                r.setCode(200);
                r.setData(new retInfo(token,
                                yh.getYhmc(),
                                yh.getYhsf()==null?"":yh.getYhsf(),
                                yh.getYhbh()
                        )
                );
                request.getSession().setAttribute(TokenTools.tokenServerkey,token);
                System.out.println( request.getSession().getAttribute("Token"));
            } else {
                r.setCode(-1);
                r.setData(new errorInfo("用户名错误", "fail"));
            }
        }

        return r;
    }
    @ResponseBody
    @RequestMapping(value = "/getYh")
    public Map<String,Object> findYhmcByYhbm(String yhbm){

        List<YhbModel> yhblist= yhbService.findYhmcByYhbm(yhbm);
        Map<String,Object> sMap = new HashMap<>();
        sMap.put("list",yhblist);

        return sMap;
    }
    @ResponseBody
    @RequestMapping(value = "/getYhb")
    public Map<String,Object> findYhmcByYhbbm(String yhbm){

        List<YHB> yhblist= yhbService.findYhmcByYhbbm(yhbm);
        Map<String,Object> sMap = new HashMap<>();
        sMap.put("list",yhblist);

        return sMap;
    }
    @ResponseBody
    @RequestMapping(value = "/getUserInfo",method = RequestMethod.POST)
    public Map getUserInfo(@RequestBody t t){
        int code =200;
        Map map = new HashMap();
        List<String> roles = new ArrayList<>();
        try {
            String yhsf= yhbService.fingSfByYhmc(t.getUsername());
            roles.add(yhsf);
            map.put("roles",roles);
        }catch (Exception E){
            code = -1;
        }

        map.put("code",code);
        return map;
    }
    @ResponseBody
    @RequestMapping(value = "/getAllUser")
    public Map getAllUser()
    {
        List<YhbModel> yhblist = yhbService.findAll();
        Map<String,Object> sMap = new HashMap<>();
        sMap.put("list",yhblist);
        return sMap;
    }

    @ResponseBody
    @RequestMapping(value = "/getAllAssessUser")
    public Map getAllAssessUser(){
        List<YHB> yhblist = yhbService.findAllAssessUser();
        Map<String,Object> sMap = new HashMap<>();
        sMap.put("list",yhblist);
        return sMap;
    }
}
