package com.nju.software.assessment.controller;

import com.nju.software.assessment.bean.yitou;
import com.nju.software.assessment.dao.VoteDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class VoteController {
    @Resource
    private VoteDao voteDao;
    @ResponseBody
    @RequestMapping(value = "/getYitou",method = RequestMethod.GET)
    public List<yitou> getAll(){
        List<yitou> res=voteDao.findAll();
        return res;
    }
}
