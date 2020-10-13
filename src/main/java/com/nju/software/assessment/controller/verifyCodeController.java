package com.nju.software.assessment.controller;

import com.nju.software.assessment.bean2.YHB;
import com.nju.software.assessment.model.*;
import com.nju.software.assessment.service.DuanxinService;
import com.nju.software.assessment.service.YhbService;
import com.nju.software.assessment.util.GetVerifyCode;
import com.nju.software.assessment.util.MyThreadPool;
import com.nju.software.assessment.util.TokenProccessor;
import com.nju.software.assessment.util.TokenTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class verifyCodeController {

    @Autowired
    YhbService yhbService;

    @Autowired
    DuanxinService duanxinService;


    //直接使用用户名和密码登录的时候，不需要CA验证和短信验证码
    @ResponseBody
    @RequestMapping(value = "/get_verifyCode",method = RequestMethod.POST)
    public response login(@RequestBody user user, HttpServletRequest request){
        String username=user.getUsername();
        YHB yh = yhbService.findYHBByYhmc(username);

        response r = new response();
        if(yh==null) {
            r.setCode(-1);
            r.setData(new errorInfo("用户不存在", "fail"));
        }
        else {
            String phone = yh.getPhone();
            String code = GetVerifyCode.getCode();
            r.setCode(200);
            //TODO 调用发送短信
            request.getSession().setAttribute(yh.getYhmc(),code);
            System.out.println( request.getSession().getAttribute(yh.getYhmc()));

        }
        return r;
    }

    //CA登录时候，需要给用户发送短信验证码
    @ResponseBody
    @RequestMapping(value="/get_verifyCode2",method= RequestMethod.POST)
    public response Login(@RequestBody CA ca, HttpServletRequest request){
        String yhdm=ca.getYhdm();
        YHB yh = yhbService.findYHBByYhdm(yhdm);

        response r = new response();
        if(yh==null) {
            r.setCode(-1);
            r.setData(new errorInfo("用户不存在", "fail"));
        }
        else {
            String phone = yh.getPhone();
            String code = GetVerifyCode.getCode();
            r.setCode(200);
            // 调用发送短信
            MyThreadPool.getInstance().execute(new Runnable() {
                @Override
                public void run() {
                    duanxinService.sendVerifyCode(phone,code);
                }
            });
            request.getSession().setAttribute(yh.getYhdm(),code);
            System.out.println( request.getSession().getAttribute(yh.getYhdm()));

        }
        return r;
    }

}
