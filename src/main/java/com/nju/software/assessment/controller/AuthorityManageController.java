package com.nju.software.assessment.controller;

import com.nju.software.assessment.bean.*;
import com.nju.software.assessment.bean.Object;
import com.nju.software.assessment.bean2.DMB;
import com.nju.software.assessment.bean2.YHB;
import com.nju.software.assessment.model.DistributionModel;
import com.nju.software.assessment.model.Yh;
import com.nju.software.assessment.model.YhbModel;
import com.nju.software.assessment.service.AuthorityService;
import com.nju.software.assessment.service.DmbService;
import com.nju.software.assessment.service.PublicAssessManageService;
import com.nju.software.assessment.service.YhbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.basic.BasicOptionPaneUI;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class AuthorityManageController {
    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private YhbService yhbService;

    @Autowired
    private DmbService dmbService;

    @Autowired
    private PublicAssessManageService publicAssessManageService;
    @ResponseBody
    @RequestMapping(value="/addAssessUser")
    public Map addAssessUser(){
        System.out.println("哈哈哈");

        //造数据
        List<Object> objectList = publicAssessManageService.findAllObject();

        List<YHB> yhbList = yhbService.findAllAssessUser();
        Random rand =new Random(10);

        List<AssessDistribution> adlist =new ArrayList<AssessDistribution>();
        //List<DistributionModel>
        List<DistributionDetail> dbdlist = new ArrayList<DistributionDetail>();


        for(YHB yhb: yhbList) {
            AssessDistribution tempad = new AssessDistribution();
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            tempad.setDistributionId(uuid);
            tempad.setDistributionState(1);
            tempad.setDistributionTime(new Date());//产生的时间可能晚八小时 根据电脑的zonetime
            tempad.setYhbh(yhb.getYhbh());
            adlist.add(tempad);
            for(Object oj:objectList) {
                String uid = UUID.randomUUID().toString().replaceAll("-","");
                DistributionDetail distributionDetail = new DistributionDetail();
                distributionDetail.setDistributionDetailId(uid);
                distributionDetail.setDistributionId(uuid);
                distributionDetail.setObjectId(oj.getObjectId());
                distributionDetail.setObjectNum(rand.nextInt(10));
                dbdlist.add(distributionDetail);
            }
        }

        publicAssessManageService.saveAllAssessDistribution(adlist);

        publicAssessManageService.saveAllDistributionDetail(dbdlist);
        //List<Dis>
       // for()
        //int i;
       // i=;
        //int count = 0;
      /*  List<UserAssessRecord> uarlist = new ArrayList<UserAssessRecord>();
        for(YHB yhb:yhbList){
            for(Object obj:objectList){
                count++;
                UserAssessRecord uartemp = new UserAssessRecord();
                uartemp.setObjectId(obj.getObjectId());
                uartemp.setObjectName(obj.getObjectName());
                uartemp.setObjectNum(Long.valueOf(rand.nextInt(10)));
                uartemp.setUserAssessRecordId(Long.valueOf(count));
                uartemp.setYhbh(Long.valueOf(yhb.getYhbh()));

                uarlist.add(uartemp);
            }
        }*/

       // publicAssessManageService.saveAllUserAssessRecord(uarlist);
       // dmbService.saveAll(dmlist);
        //authorityService.saveAllAssessUser(list);
        return null;
    }
    @ResponseBody
    @RequestMapping(value = "/addAuthority",method = RequestMethod.POST)
    public Map addAuthority(Authority auth ){//删除授予的权限
        //System.out.println(obj);

        Date date = new Date();//可能会因为系统时区和jvm的时区不一致  而晚八小时
        auth.setQxcjsj(date);

        int code = 200;
        Map map = new HashMap();
        try{
            authorityService.saveAuthority(auth);

            //peopleConfigDao.save(peopleConfig);
        }catch (Exception e){
            code = -1;
        }
        map.put("code",code);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteAuthority",method = RequestMethod.POST)
    public Map deleteAuthority(AuthorityList auth ){//删除授予的权限
        //System.out.println(obj);

        /*
        一开始想着可以批量删除权限，提高性能，后来尝试几个小时，实在不知道怎么拼接，所以只能单个单个删除了
        * */
        int code = 200;
        Map map = new HashMap();
        try{
            for(int i = 0;i<auth.getValue().size();i++) {
                for(int j = 0;j<auth.getPeopleList().size();j++) {
                    authorityService.deleteAllUserAuthorityRecord(Long.valueOf(auth.getValue().get(i)),auth.getPeopleList().get(j).get(0),Long.valueOf(auth.getPeopleList().get(j).get(1)));
                }
            }

            //peopleConfigDao.save(peopleConfig);
        }catch (Exception e){
            code = -1;
        }
        map.put("code",code);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/confirmAuthority",method = RequestMethod.POST)
    public Map confirmAuthority(AuthorityList auth ){
        //System.out.println(obj);

        //将下拉框的授权的信息处理成UserAuthorityRecord的表结构信息
        List<UserAuthorityRecord> uarlist = new ArrayList<UserAuthorityRecord>();


        for(int i = 0;i<auth.getValue().size();i++) {
            for(int j = 0;j<auth.getPeopleList().size();j++) {

                UserAuthorityRecord uar = new UserAuthorityRecord();

                uar.setQxbh(Long.valueOf(auth.getValue().get(i)));//权限编号 权限编号
                uar.setBmbh(auth.getPeopleList().get(j).get(0));//部门编号  String类型
                uar.setYhbh(Long.valueOf(auth.getPeopleList().get(j).get(1)));
                //uar.setQxbh();
                //uar.setYhqxbh(Long.valueOf(xize));
                uarlist.add(uar);
            }
        }
        int code = 200;
        Map map = new HashMap();
        try{
            authorityService.saveAllUserAuthorityRecord(uarlist);
            //peopleConfigDao.save(peopleConfig);
        }catch (Exception e){
            code = -1;
        }
        map.put("code",code);
        return map;
    }
    /*返回权限列表*/
    @ResponseBody
    @RequestMapping(value = "/getAuthorityList",method = RequestMethod.GET)
    public Map getAuthorityList(){
        int code = 200;
        Map map = new HashMap();
        try{
            List<Authority> auth=authorityService.findAllAuthority();
            map.put("data",auth);
        }
        catch (Exception e)
        {
            map.put("code",-1);
        }
        map.put("code",code);
        return map;
    }
}
