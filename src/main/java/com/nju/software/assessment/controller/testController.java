package com.nju.software.assessment.controller;

import com.nju.software.assessment.bean.*;
import com.nju.software.assessment.bean2.YHB;
import com.nju.software.assessment.dao.*;
import com.nju.software.assessment.dao2.YhbDao;
import com.nju.software.assessment.service.YhbService;
import com.nju.software.assessment.util.DateUtil;
import com.nju.software.assessment.util.ExportExcel;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.lang.Object;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.*;


@Controller
public class testController {
    @Resource
    private YhbService yhbService;
    @Resource
    private PublishDao publishDao;
    @Resource
    private YhbDao yhbDao;
    @Resource
    private ReformDao reformDao;
    @Resource
    private Static_PublishDao static_publishDao;
    @Resource
    private Static_ReformDao static_reformDao;
    @Resource
    private StaticDao staticDao;

    @ResponseBody
    @RequestMapping(value = "/getAllYitouContent",method = RequestMethod.POST)
    public Map getAllYitouContent(String id){
        int code =200;
        Map map = new HashMap();
        List<String> cols = new ArrayList<>();

        List<List<String>> rows = new ArrayList<>();
        try{
            List<Reform> reforms = reformDao.findByPublishId(id);
            if(reforms.size()>0){
                String cont = reforms.get(0).getReformContent();
                String [] contA = cont.split(";");
                for(String s:contA){
                    cols.add(s.split(":")[0]);
                }

            }
            cols.add(0,"填写日期");
            cols.add(1,"姓名");
            for(Reform reform:reforms){
                Integer userId= reform.getUserId();
                YHB yh = yhbService.findByYhbh(userId);
                String name = null;
                if(yh!=null)
                  name = yh.getYhmc();
                List<String> row = new ArrayList<>();

                String content = reform.getReformContent();
                if(content!=null&&!content.trim().equals("")){
                    String [] contents = content.split(";");
                    for(String c :contents){
                        row.add(c.split(":")[1]);
                    }
                    row.add(0,String.valueOf(reform.getReformTime()));
                    row.add(1,name);
                }

                rows.add(row);

            }
            map.put("rows",rows);
            map.put("cols",cols);
        }catch (Exception e){
            code = -1;

        }

        map.put("code",code);
        return map;

    }

    @ResponseBody
    @RequestMapping(value = "/reform",method = RequestMethod.POST)
    public Map reform(String content,String id,String name,String reformTime){
        int code = 200;
        Map map = new HashMap();
        try{
            Integer userId =yhbDao.getIdByName(name);
            List<Reform> reforms = reformDao.findByPublishIdAndUserId(id,userId);
            for(Reform reform:reforms){
                reform.setIsReform(1);
                reform.setReformContent(content);
                reform.setReformTime(DateUtil.format(reformTime));
                reformDao.save(reform);
            }
        }catch (Exception e){
            code = -1;
        }
        map.put("code",code);
        return map;


    }
    @ResponseBody
    @RequestMapping(value = "/exportExcel")
    public void exprotExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String type = request.getParameter("type");
        String title;
        String[] rowName;
        List<Object[]> tableData;
        if(type==null){
            title = request.getParameter("title");
            String[] cpx = request.getParameter("cpx").split(",");
            String[] legend = request.getParameter("legend").split(",");
            String []series = request.getParameter("series").split(",");
            rowName = new String[cpx.length+1];
            rowName[0]=" ";
            for(int i=1;i<rowName.length;i++){
                rowName[i]=cpx[i-1];
            }
            int code =200;
            Map map = new HashMap();
            tableData = new ArrayList<>();
            String[][] res= new String[legend.length][cpx.length];
            int k = 0;
            for(int i=0;i<legend.length;i++){
                for(int j = 0;j<cpx.length;j++){
                    res[i][j] = series[k];
                    k++;
                }
            }
            for(int i = 0;i<legend.length;i++){
                String[] d =new String[rowName.length];

                for(int j =0;j<rowName.length;j++){
                    if(j==0){
                        d[j] = legend[i];
                    }
                    else {
                        d[j] = res[i][j-1];
                    }

                }
                tableData.add(d);
            }

        }else {
            title = request.getParameter("title");
            String[] cols = request.getParameter("cols").split(",");
            rowName=cols;
            String[] data = request.getParameter("data").split(",");
            int rowNum = (int) data.length/cols.length;
            tableData = new ArrayList<>();
            int k = 0;
            for(int i = 0;i<rowNum;i++){
                Object[] d = new Object[cols.length];
                for(int j = 0;j<cols.length;j++){
                    d[j]=data[k];
                    k++;
                }
                tableData.add(d);
            }
        }
        String fileName = new String((title+ ".xls").getBytes("UTF-8"), "ISO-8859-1");;
        //告诉浏览器数据格式，将头和数据传到前台
        String headStr = "attachment; filename=\"" + fileName + "\"";
        response.setContentType("APPLICATION/OCTET-STREAM");
        response.setHeader("Content-Disposition", headStr);
        ExportExcel exportExcel = new ExportExcel(title,rowName,tableData);
        OutputStream os = response.getOutputStream();
        exportExcel.export(os);
    }


    @ResponseBody
    @RequestMapping(value = "/getTpStatics",method = RequestMethod.POST)
    public Map getTpStatics(String name){
        int code = 200;
        Map map = new HashMap();
        try{
            List<Publish> publishes = publishDao.findByPublisherOrderByCreateTimeDesc(name);
            map.put("customize",publishes.size());
            List<Static_Publish> static_publishes = static_publishDao.findByPublisherOrderByCreateDateDesc(name);
            Integer[] size = {0,0,0,0,0,0};
            for(Static_Publish static_publish:static_publishes){
                Integer type = static_publish.getType();
                size[type-1]+=1;
            }
            map.put("size",size);
        }catch (Exception e){
            code = -1;
        }
        map.put("code",code);
        return map;
    }
    @ResponseBody
    @RequestMapping(value = "/getMonthlyFbData",method = RequestMethod.POST)
    public Map getMonthlyFbData(String name,String year){
        int code = 200;
        Map map = new HashMap();
        try{
            List<Publish> publishes = publishDao.findByPublisherAndCreateTimeBetween(name,year+"-01-01 00:00:00",year+"-12-31 23:59:59");
            List<Static_Publish> static_publishes = static_publishDao.findByPublisherAndCreateDateBetween(name,year+"-01-01 00:00:00",year+"-12-31 23:59:59");
            Integer []data = new Integer[12];
            for(int i = 0;i<data.length;i++){
                data[i]=0;
            }
            for(Publish publish:publishes){
                String dateStr = publish.getCreateTime();
                Date date = DateUtil.StringToDate(dateStr);
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                int month = cal.get(Calendar.MONTH);
                data[month]+=1;
            }
            for(Static_Publish static_publish:static_publishes){
                String dateStr = static_publish.getCreateDate();
                Date date = DateUtil.StringToDate(dateStr);
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                int month = cal.get(Calendar.MONTH);
                data[month]+=1;
            }
            int max = 0;
            for(Integer i:data){
                if(i>max)
                    max=i;
            }
            map.put("monthlyData",data);
            map.put("max",max);
        }catch (Exception e){
            code = -1;
        }
        map.put("code",code);
        Page<Static_Publish> res = staticDao.findAllByPublisher(0,name);
        System.out.println(res);
        return map;
    }
    @ResponseBody
    @RequestMapping(value = "/getMonthlyTpData",method = RequestMethod.POST)
    public Map getMonthlyTpData(String name,String year){
        int code = 200;
        Map map = new HashMap();
        try{
            List<Reform> reforms = reformDao.findByUserIdAndReformTimeBetween(yhbDao.getIdByName(name),Timestamp.valueOf(year+"-01-01 00:00:00"),Timestamp.valueOf(year+"-12-31 23:59:59"));
            List<Static_Reform> static_reforms = static_reformDao.findByNameAndDateBetween(name,year+"-01-01 00:00:00",year+"-12-31 23:59:59");
            Integer []tpData = new Integer[12];
            for(int i = 0;i<tpData.length;i++){
                tpData[i]=0;
            }
            for(Reform reform:reforms){
                Timestamp timestamp = reform.getReformTime();
                int month =timestamp.getMonth();
                tpData[month]+=1;
            }
            for(Static_Reform static_reform:static_reforms){
                String dateStr = static_reform.getDate();
                Date date = DateUtil.StringToDate(dateStr);
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                int month = cal.get(Calendar.MONTH);
                tpData[month]+=1;
            }
            int max = 0;
            for(Integer i:tpData){
                if(i>max)
                    max=i;
            }
            map.put("monthlyTpData",tpData);
            map.put("max",max);
        }catch (Exception e){
            code = -1;
        }
        map.put("code",code);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/test",method = RequestMethod.POST)
    @Transactional(transactionManager="platformTransactionManagerOne",rollbackFor = Exception.class)
    public Map test(Static_Publish publish){
        Map map = new HashMap();
        int code = 200;
        try{
            for(int k=0;k<10000;k++){
                long start = new Date(2018,1,1,0,0,0).getTime();
                long end =new Date().getTime();
                long s = (long) Math.floor((end-start)*Math.random())+start;
                Date date = new Date(s);
                String createDate = DateUtil.dateToStrLong(date);
                publish.setCreateDate(createDate);
                publish.setEndDate(publish.getEndDate());
                publish.setStartDate(publish.getStartDate());
                String id =static_publishDao.save(publish).getId();
                List <String>cp_namelist = publish.getCp_namelist();
                List<String> bcp_namelist = publish.getBcp_namelist();
                for(String name :cp_namelist){
                    Map<String,List<List<String>>> reformMap=new HashMap<>();
                    List<List<String>> cpx =new ArrayList<>();
                    for(int i=0;i<publish.getCpx().size();i++){
                        List<String> cc=new ArrayList<>();
                        for(int j=0;j<publish.getCpx().get(i).size();j++)
                            cc.add("");
                        cpx.add(cc);
                    }

                    for(String bcpname:bcp_namelist){
                        reformMap.put(bcpname,cpx);
                    }
                    Static_Reform reform = new Static_Reform();
                    static_reformDao.save(reform);
                }
            }

        }catch (Exception e){
            code = -1;
        }
        map.put("code",code);
        return map;
    }
}
