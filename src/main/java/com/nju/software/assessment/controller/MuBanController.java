package com.nju.software.assessment.controller;

import com.nju.software.assessment.bean.MuBan;
import com.nju.software.assessment.service.MuBanService;
import com.nju.software.assessment.util.TokenTools;
import com.nju.software.assessment.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class MuBanController {

    @Autowired
    MuBanService muBanService;
    @Autowired
    UUID uuid;
//    private String fileurl = "/home/lixing/topology-ts/VoteImage/";
    private String fileurl = "D:/assessment/VoteImage/";
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @ResponseBody
    @GetMapping("/index")
    public String  index(Model model){
        return "index";
    }

    @ResponseBody
    @RequestMapping(value = "/addmuban")
    public Map<String,Object> addmuban(@RequestParam("file") MultipartFile blobFile,@RequestParam("user")String user,
                                       @RequestParam("title")String title,@RequestParam("miaoshu")String miaoshu,
                                       @RequestParam("content")String content,@RequestParam("isshare")int isshare,
                                       @RequestParam("type")int type) throws IOException {
        Date date = new Date();
        int id = 0;
        MuBan muBan = new MuBan();
        muBan.setUser(user);
        muBan.setTitle(title);
        muBan.setMiaoshu(miaoshu);
        muBan.setContent(content);
        muBan.setIsshare(isshare);
        muBan.setType(type);
        muBan.setCreatetime(formatter.format(date));

        String name = uuid.getUUID();
        String url = "vote_"+name+".png";
        muBan.setDataUrl(url);

        File f = null;
        f = new File(fileurl+url);

        try (InputStream in  = blobFile.getInputStream(); OutputStream os = new FileOutputStream(f)){
            // 得到文件流。以文件流的方式输出到新文件
            // 可以使用byte[] ss = multipartFile.getBytes();代替while
            byte[] buffer = new byte[4096];
            int n;
            while ((n = in.read(buffer,0,4096)) != -1){
                os.write(buffer,0,n);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        try{
            id = muBanService.addMuBan(muBan);
            System.out.println(id);
        }catch (Exception e){
            System.out.println(e);
        }
        System.out.println(muBan.toString());
        Map<String,Object> sMap = new HashMap<>();
        sMap.put("id",id);
        return sMap;
    }

    @ResponseBody
    @RequestMapping(value = "/altermuban")
    public void altermuban(MuBan muBan){
        Date date = new Date();
        muBan.setCreatetime(formatter.format(date));
        try{
            muBanService.addMuBan(muBan);
        }catch (Exception e){
            System.out.println(e);
        }
        System.out.println(muBan.toString());
    }


    @ResponseBody
    @RequestMapping(value = "/getmuban")
    public Map<String,Object> getMuBanByUserAndIsshare(String user, Integer isshare, HttpServletRequest request){
        Map<String, Object> sMap = new HashMap<>();
//        if(TokenTools.judgeTokenIsEqual(request)) {
        List<MuBan> muBanList = new ArrayList<>();
        if(isshare == 1){
            muBanList = muBanService.getMuBanbyIsshare(isshare);
        }else if(isshare == 0) {
            muBanList = muBanService.getMuBanbyUserAndIsshare(user, isshare);

        }
        int size = muBanList.size();
        sMap.put("code", 200);
        sMap.put("count", size);
        sMap.put("list", muBanList);

//        }else {
//            sMap.put("code",5004);
//        }
        return sMap;
    }

    @ResponseBody
    @RequestMapping(value = "/updateIsshare")
    public void updateMuBanByIsshare(Integer isshare,Integer id){
        muBanService.updateMuBanByIsshare(isshare,id);
    }

    @ResponseBody
    @RequestMapping(value = "/deleteMuban")
    public void deleteMuBanById(Integer id){
        muBanService.deleteMuBanById(id);

    }

}
