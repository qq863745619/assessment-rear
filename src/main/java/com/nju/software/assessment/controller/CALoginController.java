package com.nju.software.assessment.controller;

import com.nju.software.assessment.model.*;
import com.nju.software.assessment.service.AuthorityService;
import com.nju.software.assessment.service.YhbService;
import com.nju.software.assessment.util.PropertiUtil;
import com.nju.software.assessment.util.TokenProccessor;
import com.nju.software.assessment.util.TokenTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CALoginController {

    @Autowired
    YhbService yhbService;

    private static final Logger logger = LoggerFactory.getLogger(CALoginController.class);

    @Autowired
    AuthorityService authorityService;
    //验证码加CA双重验证登录
    @ResponseBody
    @RequestMapping(value="/Login.do",method= RequestMethod.POST)
    public response Login(@RequestBody CA ca, HttpServletRequest request){
        System.out.println(ca.getYhdm());

        LoginInfo loginInfo = yhbService.findByYhdm(ca.getYhdm());
        response r = new response();
        if(loginInfo==null) {
            r.setCode(-1);
            r.setData(new errorInfo("用户不存在", "fail"));
        }else {
            //判断验证码是否存在
            if(ca.getVerifyCode()!=null&&ca.getVerifyCode().equals(request.getSession().getAttribute(loginInfo.getYhdm()))){
                if (loginInfo.getYhkl().equals(ca.getYhkl())) {

                    String token = TokenProccessor.getInstance().makeToken();
                    System.out.println(token);
                    r.setCode(200);
                    r.setData(new retInfo(token, loginInfo.getYhmc(),loginInfo.getYhsf()==null?"":loginInfo.getYhsf(),loginInfo.getYhbh()));
                    request.getSession().setAttribute(TokenTools.tokenServerkey,token);
                    System.out.println( request.getSession().getAttribute("Token"));
                } else {
                    r.setCode(-1);
                    r.setData(new errorInfo("用户名错误", "fail"));
                }
            }else {
                r.setCode(-1);
                r.setData(new errorInfo("验证码错误", "fail"));
            }

        }

        return r;
    }

}
