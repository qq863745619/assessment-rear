package com.nju.software.assessment.controller;


import com.nju.software.assessment.bean.Static_Publish;
import com.nju.software.assessment.bean.Static_Reform;
import com.nju.software.assessment.dao.Static_PublishDao;
import com.nju.software.assessment.dao.Static_ReformDao;
import com.nju.software.assessment.service.DuanxinService;
import com.nju.software.assessment.service.YhbService;
import com.nju.software.assessment.util.DateUtil;
import com.nju.software.assessment.util.MyThreadPool;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;

@Controller
public class Static_PublishController {
    @Resource
    private Static_PublishDao static_publishDao;
    @Resource
    private Static_ReformDao static_reformDao;

    @Autowired
    DuanxinService duanxinService;

    @Autowired
    YhbService yhbService;

    @ResponseBody
    @RequestMapping(value = "/deleteStaticPublish",method = RequestMethod.POST)
    public Map deleteStaticPublish(String id){
        Map map = new HashMap();
        int code = 200;
        try{
            static_publishDao.deleteById(id);
            List<Static_Reform> reforms = static_reformDao.findByStaticPublishId(id);
            for(Static_Reform reform:reforms){
                static_reformDao.deleteAllByStaticPublishId(id);
            }
        }catch (Exception e){
            code = -1;
        }
        map.put("code",code);
        return map;
    }
    @ResponseBody
    @RequestMapping(value = "/deleteStaticPublishBatch",method = RequestMethod.POST)
    public Map deleteStaticPublishBatch(String ids){
        Map map = new HashMap();
        int code = 200;
        String []idsArr =  ids.split(",");
        try{
            for(String id:idsArr){
                static_publishDao.deleteById(id);
                List<Static_Reform>reforms = static_reformDao.findByStaticPublishId(id);
                for(Static_Reform reform:reforms){
                    static_reformDao.deleteAllByStaticPublishId(id);
                }
            }

        }catch (Exception e){
            code = -1;
        }
        map.put("code",code);
        return map;
    }
    @ResponseBody
    @RequestMapping(value = "/finishStaticPublishBatch",method = RequestMethod.POST)
    public Map finishStaticPublishBatch(String ids){
        Map map = new HashMap();
        int code = 200;
        String []idsArr =  ids.split(",");
        try{
            for(String id:idsArr){
                Static_Publish static_publish =static_publishDao.findAllById(id);
                if(DateUtil.StringToDate(static_publish.getEndDate()).getTime()<new Date().getTime()){
                    continue;
                }
                static_publish.setEndDate(DateUtil.dateToStrLong(new Date()));
                static_publishDao.save(static_publish);
            }

        }catch (Exception e){
            code = -1;
        }
        map.put("code",code);
        return map;
    }
    @ResponseBody
    @RequestMapping(value = "/static_publish",method = RequestMethod.POST)
    @Transactional(transactionManager="platformTransactionManagerOne",rollbackFor = Exception.class)
    public Map static_publish(Static_Publish publish){
        Map map = new HashMap();
        int code = 200;
        try{
            String createDate = DateUtil.formatStr(publish.getCreateDate());
            publish.setCreateDate(createDate);
            publish.setEndDate(publish.getEndDate());
            publish.setStartDate(publish.getStartDate());
            String id =static_publishDao.save(publish).getId();
            //获取到选择的投票人列表
            List <String>cp_namelist = publish.getCp_namelist();
            //被测评人列表
            List<String> bcp_namelist = publish.getBcp_namelist();
            for(String name :cp_namelist){
                Map<String,List<List<String>>> reformMap=new HashMap<>();
                //获取所有的测评项
                List<List<String>> cpx =new ArrayList<>();
                for(int i=0;i<publish.getCpx().size();i++){
                    List<String> cc=new ArrayList<>();
                    for(int j=0;j<publish.getCpx().get(i).size();j++)
                        cc.add("");
                    cpx.add(cc);
                }
                //存放被测评人和测评项列表，在之后的投票时更改
                for(String bcpname:bcp_namelist){
                    reformMap.put(bcpname,cpx);
                }
                Static_Reform reform = new Static_Reform();
                reform.setShow("yes");
                reform.setBz(new ArrayList<>());
                reform.setDate("");
                reform.setIsReform("no");
                reform.setReform(reformMap);
                reform.setCreateDate(createDate);
                reform.setDepartment(publish.getDepartment());
                reform.setEndDate(publish.getEndDate());
                reform.setStaticPublishId(id);
                reform.setLevel(publish.getLevel());
                reform.setName(name);
                reform.setNote(publish.getNote());
                reform.setPublisher(publish.getPublisher());
                reform.setRatio(publish.getRatio());
                reform.setStartDate(publish.getStartDate());
                reform.setTitle(publish.getTitle());
                reform.setValue(publish.getValue());
                reform.setDate(publish.getDate());
                static_reformDao.save(reform);
                String phone = yhbService.findPhoneByYhmc(name);
                System.out.println(phone);
//                MyThreadPool.getInstance().execute(new Runnable() {
//                    @Override
//                    public void run() {
//                        String endDate = publish.getEndDate();
//                        duanxinService.sendNotify(phone,endDate);
//                    }
//                });
            }
        }catch (Exception e){
            code = -1;
        }
        map.put("code",code);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/static_reform",method = RequestMethod.POST)
    public Map static_reform(String id,String name,String reform,String bz){
        int code =200;
        Map map = new HashMap();
        JSONObject json=JSONObject.fromObject(reform);
        Map<String,List<List<String>>> re = new HashMap<>();
        re=json;
        try{
            List<Static_Reform >reforms = static_reformDao.findByStaticPublishIdAndName(id,name);
            if(reforms.size()>0){
                Static_Reform Reform = reforms.get(0);
                Reform.setDate(DateUtil.dateToStrLong(new Date()));
                Reform.setReform(re);
                Reform.setIsReform("yes");
                Reform.setBz(Arrays.asList(bz.split(",")));
                static_reformDao.save(Reform);
                Static_Publish static_publish = static_publishDao.findAllById(id);
                List<Static_Reform> static_reforms = static_reformDao.findByStaticPublishId(id);
                int count = 0;
                for(Static_Reform static_reform:static_reforms){
                    if(static_reform.getIsReform().equals("yes"))
                        count++;
                }
                static_publish.setRatio((int) Math.floor((count/Double.valueOf(static_reforms.size()))*100));
                static_publishDao.save(static_publish);

            }else code=-1;
        }catch (Exception e){
            code=-1;
        }
        map.put("code",code);
        return map;

    }

    @ResponseBody
    @RequestMapping(value = "/getYitouData",method = RequestMethod.POST)
    public Map getYitouData(String publishId){
        int code =200;
        Map map = new HashMap();
        List<List<List<List<Integer>>>> list = new ArrayList<>();
        List<Map<String,List<List<String>>>> detail = new ArrayList<>();
        try{

            Static_Publish publish = static_publishDao.findAllById(publishId);
            List<List<String>> cpx = publish.getCpx();
            List<String> cpr = publish.getCp_namelist();
            List<String> bcpr = publish.getBcp_namelist();
            List<List<String>> legend =publish.getLegend();
            map.put("bcpr",bcpr);
            map.put("cpx",cpx);
            map.put("legend",legend);
            map.put("cpr",cpr);
            List<Static_Reform> reforms = static_reformDao.findByStaticPublishIdAndIsReform(publishId,"yes");
            for(int i = 0;i<bcpr.size();i++){
                List<List<List<Integer>>> list1= new ArrayList<>();
                for(int j = 0;j<legend.size();j++){
                    List<List<Integer>> list2 = new ArrayList<>();
                    if(legend.get(j).size()>1){
                        for(int k=0;k<legend.get(j).size();k++){

                            List<Integer> list3 = new ArrayList<>();
                            for(int l=0;l<cpx.get(j).size();l++){
                                int b =0;
                                for(int m = 0;m<reforms.size();m++){
                                    List<String> c = legend.get(j);
                                    String a= reforms.get(m).getReform().get(bcpr.get(i)).get(j).get(l);
                                    if(legend.get(j).get(k).equals(a))
                                        b++;

                                }
                                list3.add(b);
                            }
                            list2.add(list3);

                        }
                        list1.add(list2);
                    }



                }
                list.add(list1);
            }
            List<Static_Reform> reforms1 = static_reformDao.findByStaticPublishId(publishId);
            List<List<String>> bzs=new ArrayList<>();
            List<String> isReform = new ArrayList<>();
            for(int n = 0;n<reforms1.size();n++){
                if(reforms1.get(n).getIsReform().equals("yes")){
                    isReform.add("yes");
                }else isReform.add("no");
                detail.add(reforms1.get(n).getReform());
                bzs.add(reforms1.get(n).getBz());
            }
            map.put("isReform",isReform);
            map.put("bzs",bzs);
        }catch (Exception e){
            code=-1;
        }

        map.put("data",list);
        map.put("code",code);
        map.put("detail",detail);
        return map;
    }
    @ResponseBody
    @RequestMapping(value = "/getOneYitouData",method = RequestMethod.POST)
    public Map getOneYitouData(String publishId,String name,boolean destory){
        int code =200;
        Map map = new HashMap();
        List<Map<String,List<List<String>>>> detail = new ArrayList<>();
        try{

            Static_Publish publish = static_publishDao.findAllById(publishId);
            List<List<String>> cpx = publish.getCpx();
            List<String> cpr = publish.getCp_namelist();
            List<String> bcpr = publish.getBcp_namelist();
            List<List<String>> legend =publish.getLegend();
            map.put("bcpr",bcpr);
            map.put("cpx",cpx);
            map.put("legend",legend);
            map.put("cpr",cpr);

            List<Static_Reform> reforms = static_reformDao.findByStaticPublishIdAndName(publishId,name);
            List<String> bz =reforms.get(0).getBz();
            map.put("bz",bz);
            for(int n = 0;n<reforms.size();n++){
                detail.add(reforms.get(n).getReform());

            }
            if(destory){
                reforms.get(0).setShow("no");
                static_reformDao.save(reforms.get(0));
            }
        }catch (Exception e){
            code=-1;
        }
        map.put("code",code);
        map.put("detail",detail);
        return map;
    }
}
