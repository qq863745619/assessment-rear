package com.nju.software.assessment.controller;


import com.alibaba.druid.sql.visitor.functions.Char;
import com.nju.software.assessment.bean.*;
import com.nju.software.assessment.bean2.DMB;
import com.nju.software.assessment.bean2.YHB;
import com.nju.software.assessment.dao.DepartAssessRecordDao;
import com.nju.software.assessment.model.SecretComputerModel;
import com.nju.software.assessment.model.UadExtModel;
import com.nju.software.assessment.service.DmbService;
import com.nju.software.assessment.service.PublicAssessManageService;
import com.nju.software.assessment.service.TaiZangService;
import com.nju.software.assessment.service.YhbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.Object;
import java.util.*;

@Controller
public class TaiZangController {

    @Autowired
    TaiZangService taiZangService;

    @Autowired
    YhbService yhbService;

    @Autowired
    DmbService dmbService;

    @Autowired
    PublicAssessManageService publicAssessManageService;

    @ResponseBody
    @RequestMapping(value = "/secretComputer",method = RequestMethod.POST)
    public Map<String,Object> secretComputer(@RequestBody SecretComputerModel secretComputerModel){
        List<String> bmList = secretComputerModel.getBmList();
        int isSecret = secretComputerModel.getIsSecret();


        int isComputer = secretComputerModel.getIsComputer();
        System.out.println("$$$$$$$"+isComputer);
        Map<String,Object> map = new HashMap<>();
        List<Long> objectsId = null;
        if(isComputer==1) {//找出所有电脑objectId
            objectsId = taiZangService.findAllComputer();
        }
        else if(isComputer==0) {//找出所有自动办公化设备编号
            objectsId = taiZangService.findAllAutoDevice();
        }
        if(bmList.indexOf("-1")!=-1)//全院范围
        {
            List<UserAssessRecord> uarList = taiZangService.findUarSecCom(objectsId);
            List<Long> uarIdList = new ArrayList<>();
            for(UserAssessRecord uar:uarList){
                uarIdList.add(uar.getUserAssessRecordId());
            }
            List<UserAssessDetail> uadList = taiZangService.findByRecordIdList(uarIdList,isSecret);
            List<UadExtModel> uadExtModels = new ArrayList<>();
            for(UserAssessDetail uadt:uadList){
                String objectName = taiZangService.findObjectNameByProductId(uadt.getProductId());
                String productName = taiZangService.findProductNameByProductId(uadt.getProductId());
                uadExtModels.add(new UadExtModel(uadt,objectName,productName));
            }
            map.put("uadList",uadExtModels);
        }
        else{//某个部门  或者某些
            List<YHB> yhbs = yhbService.findByYhbmList(bmList);
            List<Integer> list = new ArrayList<>();
            for(YHB yh:yhbs){
                list.add(yh.getYhbh());
            }

            List<UserAssessRecord> uarList = taiZangService.findUarByYhbhList(list,objectsId);

            List<Long> uarIdList = new ArrayList<>();
            for(UserAssessRecord uar:uarList){
                uarIdList.add(uar.getUserAssessRecordId());
            }
            List<UserAssessDetail> uadList = taiZangService.findByRecordIdList(uarIdList,isSecret);

            List<UadExtModel> uadExtModels = new ArrayList<>();
            for(UserAssessDetail uadt:uadList){
                String objectName = taiZangService.findObjectNameByProductId(uadt.getProductId());
                String productName = taiZangService.findProductNameByProductId(uadt.getProductId());
                uadExtModels.add(new UadExtModel(uadt,objectName,productName));
            }
            map.put("uadList",uadExtModels);
        }

        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/getObjectNameByProductId")
    public Map<String,Object> getObjectNameByProductId(String productId){
        String ObjectName = taiZangService.findObjectNameByProductId(Long.valueOf(productId));
//        System.out.println("$$$$$$$$$$$$$$"+productId);
//        System.out.println("$$$$$$$$$$$$$$"+Long.valueOf(productId));
//        System.out.println("$$$$$$$$$$$$$$"+ObjectName);
        Map<String,Object> map = new HashMap<>();
        map.put("objectName",ObjectName);
        return  map;
    }

    @ResponseBody
    @RequestMapping(value = "/copyData")
    public Map<String,Object> copyData(){


        StringBuilder stringBuilder = new StringBuilder();
        Stack<Character> s = new Stack<>();
        Stack<Character> integers = new Stack<>();
        stringBuilder.append(s.pop());
        stringBuilder.toString();
        String str = "";
        //str.
        s.push(s.get(2));









        List<Tempsix> tempones = taiZangService.findAllTempone();
        for(Tempsix tempone:tempones){
            String name = tempone.getName();
           // if()

            YHB yh = yhbService.findYHBByYhmc(name);
            DMB dmb = dmbService.findByBmmc(tempone.getDepartName());
            Product pro = taiZangService.findProductByName(tempone.getProductType());

            if(yh==null){//用户不存在
                if(dmb==null){
                    System.out.println("$$$$$$$$$$$$$$$$"+tempone.getIndex());
                    continue;
                }
                //DMB dmb = dmbService.findByBmmc(tempone.getDepartName());

                DepartmentAssessRecord temp_dar = taiZangService.findByObjectAndDmbh(dmb.getDmbh(),pro.getObjectId());
                UserAssessDetail userAssessDetail = new UserAssessDetail();
                userAssessDetail.setIsDepartAssess(1);//设置是否为部门资产
                userAssessDetail.setAddressId(tempone.getRoomNum());//设置为部门房间号
                if(temp_dar==null){
                    Long darid = Long.valueOf(taiZangService.findDarMaxId()+1);
                    DepartmentAssessRecord departmentAssessRecord = new DepartmentAssessRecord();
                    departmentAssessRecord.setDepartmentAssessRecordId(darid);
                    departmentAssessRecord.setDmbh(dmb.getDmbh());
                    departmentAssessRecord.setObjectId(pro.getObjectId());
                    departmentAssessRecord.setObjectName(taiZangService.findObjectNameByProductId(pro.getProductId()));
                    departmentAssessRecord.setObjectNum(Long.valueOf(1));
                    taiZangService.saveDar(departmentAssessRecord);
                    userAssessDetail.setUserAssessRecordId(darid);
                }else{
                    temp_dar.setObjectNum(temp_dar.getObjectNum()+1);
                    taiZangService.saveDar(temp_dar);
                    userAssessDetail.setUserAssessRecordId(temp_dar.getDepartmentAssessRecordId());
                }

                Address temp_address = taiZangService.findAddress(tempone.getRoomNum());
                if(temp_address==null&&tempone.getRoomNum()!=null&&!tempone.getRoomNum().equals("")){
                    Address address = new Address();
                    address.setAddressId(tempone.getRoomNum());
                    address.setAddressName(tempone.getDepartName());
                    address.setAddressBmbh(dmb.getDmbh());
                    taiZangService.saveAddress(address);
                    //userAssessDetail.setAddressId();
                }


                userAssessDetail.setProductId(pro.getProductId());



                //设置detail的一些参数
                userAssessDetail.setUserAssessDetailId(taiZangService.findUadMaxId()+1);
                //userAssessDetail.setUserAssessRecordId();
                userAssessDetail.setTip(tempone.getTip());
                userAssessDetail.setUserName(tempone.getName());
                userAssessDetail.setIsSecret(0);
                userAssessDetail.setMacIp(tempone.getIp());



                taiZangService.saveUserAssessDetail(userAssessDetail);
                //taiZangService.sav

                //product.set
                //userAssessDetail.setResponseYhbh();
                //userAssessDetail.setAddressId(/tempone.getRoomNum());


            }
            else{
                //DMB dmb = dmbService.findByBmmc(tempone.getDepartName());
                dmb = dmbService.findByBmdm(yh.getYhbm());
                UserAssessRecord temp_dar = taiZangService.findUarByObjectAndDmbh(Long.valueOf(yh.getYhbh()),pro.getObjectId());
                UserAssessDetail userAssessDetail = new UserAssessDetail();
                userAssessDetail.setIsDepartAssess(0);//设置是否为部门资产
                userAssessDetail.setAddressId(tempone.getRoomNum());//设置为部门房间号
                if(temp_dar==null){
                    Long darid = Long.valueOf(taiZangService.findUarMaxId()+1);
                    UserAssessRecord departmentAssessRecord = new UserAssessRecord();
                    departmentAssessRecord.setUserAssessRecordId(darid);
                    departmentAssessRecord.setYhbh(Long.valueOf(yh.getYhbh()));
                    departmentAssessRecord.setObjectId(pro.getObjectId());
                    departmentAssessRecord.setObjectName(taiZangService.findObjectNameByProductId(pro.getProductId()));
                    departmentAssessRecord.setObjectNum(Long.valueOf(1));
                    taiZangService.saveUar(departmentAssessRecord);
                    userAssessDetail.setUserAssessRecordId(darid);
                }else{
                    temp_dar.setObjectNum(temp_dar.getObjectNum()+1);
                    taiZangService.saveUar(temp_dar);
                    userAssessDetail.setUserAssessRecordId(temp_dar.getUserAssessRecordId());
                }

                Address temp_address = taiZangService.findAddress(tempone.getRoomNum());
                if(temp_address==null&&tempone.getRoomNum()!=null&&!tempone.getRoomNum().equals("")){
                    Address address = new Address();
                    address.setAddressId(tempone.getRoomNum());
                    address.setAddressName(tempone.getDepartName());
                    address.setAddressBmbh(dmb.getDmbh());
                    taiZangService.saveAddress(address);
                    //userAssessDetail.setAddressId();
                }


                userAssessDetail.setProductId(pro.getProductId());



                //设置detail的一些参数
                userAssessDetail.setUserAssessDetailId(taiZangService.findUadMaxId()+1);
                //userAssessDetail.setUserAssessRecordId();
                userAssessDetail.setTip(tempone.getTip());
                userAssessDetail.setUserName(tempone.getName());
                userAssessDetail.setIsSecret(0);
                userAssessDetail.setMacIp(tempone.getIp());




                taiZangService.saveUserAssessDetail(userAssessDetail);
                //taiZangService.sav

                //product.set
                //userAssessDetail.setResponseYhbh();
                //userAssessDetail.setAddressId(/tempone.getRoomNum());

            }
        }
//        //tempfive
//        List<Tempfive> tempones = taiZangService.findAllTempone();
//        for(Tempfive tempone:tempones){
//            String name = tempone.getName();
//            // if()
//
//            YHB yh = yhbService.findYHBByYhmc(name);
//            DMB dmb = dmbService.findByBmmc(tempone.getDepartName());
//
//            if(yh==null){//用户不存在
//                if(dmb==null){
//                    System.out.println("$$$$$$$$$$$$$$$$"+tempone.getIndex());
//                    continue;
//                }
//                //DMB dmb = dmbService.findByBmmc(tempone.getDepartName());
//                DepartmentAssessRecord temp_dar = taiZangService.findByObjectAndDmbh(dmb.getDmbh(),Long.valueOf(tempone.getProductType()));
//                UserAssessDetail userAssessDetail = new UserAssessDetail();
//                userAssessDetail.setIsDepartAssess(1);//设置是否为部门资产
//                userAssessDetail.setAddressId(tempone.getRoomNum());//设置为部门房间号
//                if(temp_dar==null){
//                    Long darid = Long.valueOf(taiZangService.findDarMaxId()+1);
//                    DepartmentAssessRecord departmentAssessRecord = new DepartmentAssessRecord();
//                    departmentAssessRecord.setDepartmentAssessRecordId(darid);
//                    departmentAssessRecord.setDmbh(dmb.getDmbh());
//                    departmentAssessRecord.setObjectId(Long.valueOf(tempone.getProductType()));
//                    if(tempone.getProductType().equals("1")){
//                    departmentAssessRecord.setObjectName("台式计算机");
//                    }
//                    else{
//                        departmentAssessRecord.setObjectName("便携式计算机");
//                    }
//                    departmentAssessRecord.setObjectNum(Long.valueOf(1));
//                    taiZangService.saveDar(departmentAssessRecord);
//                    userAssessDetail.setUserAssessRecordId(darid);
//                }else{
//                    temp_dar.setObjectNum(temp_dar.getObjectNum()+1);
//                    taiZangService.saveDar(temp_dar);
//                    userAssessDetail.setUserAssessRecordId(temp_dar.getDepartmentAssessRecordId());
//                }
//
//                Address temp_address = taiZangService.findAddress(tempone.getRoomNum());
//                if(temp_address==null&&tempone.getRoomNum()!=null&&!tempone.getRoomNum().equals("")){
//                    Address address = new Address();
//                    address.setAddressId(tempone.getRoomNum());
//                    address.setAddressName(tempone.getDepartName());
//                    address.setAddressBmbh(dmb.getDmbh());
//                    taiZangService.saveAddress(address);
//                    //userAssessDetail.setAddressId();
//                }
//
//
//                if(tempone.getProductType().equals("1"))
//                {
//                    userAssessDetail.setProductId(Long.valueOf(3));
//                }else{
//                    userAssessDetail.setProductId(Long.valueOf(14));
//                }
//
//
//
//                //设置detail的一些参数
//                userAssessDetail.setUserAssessDetailId(taiZangService.findUadMaxId()+1);
//                //userAssessDetail.setUserAssessRecordId();
//                userAssessDetail.setTip(tempone.getTip());
//                userAssessDetail.setUserName(tempone.getName());
//                userAssessDetail.setIsSecret(0);
//                userAssessDetail.setMacIp(tempone.getIp());
//               // userAssessDetail.set
//
//
//
//                taiZangService.saveUserAssessDetail(userAssessDetail);
//                //taiZangService.sav
//
//                //product.set
//                //userAssessDetail.setResponseYhbh();
//                //userAssessDetail.setAddressId(/tempone.getRoomNum());
//
//
//            }
//            else{
//                //DMB dmb = dmbService.findByBmmc(tempone.getDepartName());
//                dmb = dmbService.findByBmdm(yh.getYhbm());
//                UserAssessRecord temp_dar = taiZangService.findUarByObjectAndDmbh(Long.valueOf(yh.getYhbh()),Long.valueOf(tempone.getProductType()));
//                UserAssessDetail userAssessDetail = new UserAssessDetail();
//                userAssessDetail.setIsDepartAssess(0);//设置是否为部门资产
//                userAssessDetail.setAddressId(tempone.getRoomNum());//设置为部门房间号
//                if(temp_dar==null){
//                    Long darid = Long.valueOf(taiZangService.findUarMaxId()+1);
//                    UserAssessRecord departmentAssessRecord = new UserAssessRecord();
//                    departmentAssessRecord.setUserAssessRecordId(darid);
//                    departmentAssessRecord.setYhbh(Long.valueOf(yh.getYhbh()));
//                    if(tempone.getProductType().equals("1")){
//                        departmentAssessRecord.setObjectName("台式计算机");
//                    }
//                    else{
//                        departmentAssessRecord.setObjectName("便携式计算机");
//                    }
//                    departmentAssessRecord.setObjectNum(Long.valueOf(1));
//                    taiZangService.saveUar(departmentAssessRecord);
//                    userAssessDetail.setUserAssessRecordId(darid);
//                }else{
//                    temp_dar.setObjectNum(temp_dar.getObjectNum()+1);
//                    taiZangService.saveUar(temp_dar);
//                    userAssessDetail.setUserAssessRecordId(temp_dar.getUserAssessRecordId());
//                }
//
//                Address temp_address = taiZangService.findAddress(tempone.getRoomNum());
//                if(temp_address==null&&tempone.getRoomNum()!=null&&!tempone.getRoomNum().equals("")){
//                    Address address = new Address();
//                    address.setAddressId(tempone.getRoomNum());
//                    address.setAddressName(tempone.getDepartName());
//                    address.setAddressBmbh(dmb.getDmbh());
//                    taiZangService.saveAddress(address);
//                    //userAssessDetail.setAddressId();
//                }
//
//
//                if(tempone.getProductType().equals("1"))
//                {
//                    userAssessDetail.setProductId(Long.valueOf(3));
//                }else{
//                    userAssessDetail.setProductId(Long.valueOf(14));
//                }
//
//
//                //设置detail的一些参数
//                userAssessDetail.setUserAssessDetailId(taiZangService.findUadMaxId()+1);
//                //userAssessDetail.setUserAssessRecordId();
//                userAssessDetail.setTip(tempone.getTip());
//                userAssessDetail.setUserName(tempone.getName());
//                userAssessDetail.setIsSecret(0);
//                userAssessDetail.setMacIp(tempone.getIp());
//
//
//
//
//                taiZangService.saveUserAssessDetail(userAssessDetail);
//                //taiZangService.sav
//
//                //product.set
//                //userAssessDetail.setResponseYhbh();
//                //userAssessDetail.setAddressId(/tempone.getRoomNum());
//
//            }
//        }
        //tempfour
//        List<Tempfour> tempones = taiZangService.findAllTempone();
//        for(Tempfour tempone:tempones){
//            String name = tempone.getUserName();
//           // if()
//
//            YHB yh = yhbService.findYHBByYhmc(name);
//            DMB dmb = dmbService.findByBmmc(tempone.getDepartName());
//
//            if(yh==null){//用户不存在
//                if(dmb==null){
//                    System.out.println("$$$$$$$$$$$$$$$$"+tempone.getIndex());
//                    continue;
//                }
//                //DMB dmb = dmbService.findByBmmc(tempone.getDepartName());
//                DepartmentAssessRecord temp_dar = taiZangService.findByObjectAndDmbh(dmb.getDmbh(),Long.valueOf(1));
//                UserAssessDetail userAssessDetail = new UserAssessDetail();
//                userAssessDetail.setIsDepartAssess(1);//设置是否为部门资产
//                userAssessDetail.setAddressId(tempone.getRoomNum());//设置为部门房间号
//                if(temp_dar==null){
//                    Long darid = Long.valueOf(taiZangService.findDarMaxId()+1);
//                    DepartmentAssessRecord departmentAssessRecord = new DepartmentAssessRecord();
//                    departmentAssessRecord.setDepartmentAssessRecordId(darid);
//                    departmentAssessRecord.setDmbh(dmb.getDmbh());
//                    departmentAssessRecord.setObjectId(Long.valueOf(1));
//                    departmentAssessRecord.setObjectName("台式计算机");
//                    departmentAssessRecord.setObjectNum(Long.valueOf(1));
//                    taiZangService.saveDar(departmentAssessRecord);
//                    userAssessDetail.setUserAssessRecordId(darid);
//                }else{
//                    temp_dar.setObjectNum(temp_dar.getObjectNum()+1);
//                    taiZangService.saveDar(temp_dar);
//                    userAssessDetail.setUserAssessRecordId(temp_dar.getDepartmentAssessRecordId());
//                }
//
//                Address temp_address = taiZangService.findAddress(tempone.getRoomNum());
//                if(temp_address==null&&tempone.getRoomNum()!=null&&!tempone.getRoomNum().equals("")){
//                    Address address = new Address();
//                    address.setAddressId(tempone.getRoomNum());
//                    address.setAddressName(tempone.getDepartName());
//                    address.setAddressBmbh(dmb.getDmbh());
//                    taiZangService.saveAddress(address);
//                    //userAssessDetail.setAddressId();
//                }
//
//
//                Product temp_product = taiZangService.findProductByName(tempone.getProductType());
//                if(temp_product==null){
//                    Long productId = taiZangService.findProductMaxId()+1;
//                    Product product = new Product();
//                    product.setObjectId(Long.valueOf(1));
//                    product.setProductName(tempone.getProductType());
//                    product.setProductId(productId);
//                    taiZangService.saveProduct(product);
//                    userAssessDetail.setProductId(productId);
//                }else{
//                    userAssessDetail.setProductId(temp_product.getProductId());
//                }
//
//
//
//                //设置detail的一些参数
//                userAssessDetail.setUserAssessDetailId(taiZangService.findUadMaxId()+1);
//                //userAssessDetail.setUserAssessRecordId();
//                userAssessDetail.setTip(tempone.getTip());
//                userAssessDetail.setUserName(tempone.getUserName());
//                userAssessDetail.setIsSecret(0);
//                userAssessDetail.setMacIp(tempone.getIp());
//
//
//
//                taiZangService.saveUserAssessDetail(userAssessDetail);
//                //taiZangService.sav
//
//                //product.set
//                //userAssessDetail.setResponseYhbh();
//                //userAssessDetail.setAddressId(/tempone.getRoomNum());
//
//
//            }
//            else{
//                //DMB dmb = dmbService.findByBmmc(tempone.getDepartName());
//                dmb = dmbService.findByBmdm(yh.getYhbm());
//                UserAssessRecord temp_dar = taiZangService.findUarByObjectAndDmbh(Long.valueOf(yh.getYhbh()),Long.valueOf(1));
//                UserAssessDetail userAssessDetail = new UserAssessDetail();
//                userAssessDetail.setIsDepartAssess(0);//设置是否为部门资产
//                userAssessDetail.setAddressId(tempone.getRoomNum());//设置为部门房间号
//                if(temp_dar==null){
//                    Long darid = Long.valueOf(taiZangService.findUarMaxId()+1);
//                    UserAssessRecord departmentAssessRecord = new UserAssessRecord();
//                    departmentAssessRecord.setUserAssessRecordId(darid);
//                    departmentAssessRecord.setYhbh(Long.valueOf(yh.getYhbh()));
//                    departmentAssessRecord.setObjectId(Long.valueOf(1));
//                    departmentAssessRecord.setObjectName("台式计算机");
//                    departmentAssessRecord.setObjectNum(Long.valueOf(1));
//                    taiZangService.saveUar(departmentAssessRecord);
//                    userAssessDetail.setUserAssessRecordId(darid);
//                }else{
//                    temp_dar.setObjectNum(temp_dar.getObjectNum()+1);
//                    taiZangService.saveUar(temp_dar);
//                    userAssessDetail.setUserAssessRecordId(temp_dar.getUserAssessRecordId());
//                }
//
//                Address temp_address = taiZangService.findAddress(tempone.getRoomNum());
//                if(temp_address==null&&tempone.getRoomNum()!=null&&!tempone.getRoomNum().equals("")){
//                    Address address = new Address();
//                    address.setAddressId(tempone.getRoomNum());
//                    address.setAddressName(tempone.getDepartName());
//                    address.setAddressBmbh(dmb.getDmbh());
//                    taiZangService.saveAddress(address);
//                    //userAssessDetail.setAddressId();
//                }
//
//
//                Product temp_product = taiZangService.findProductByName(tempone.getProductType());
//                if(temp_product==null){
//                    Long productId = taiZangService.findProductMaxId()+1;
//                    Product product = new Product();
//                    product.setObjectId(Long.valueOf(1));
//                    product.setProductName(tempone.getProductType());
//                    product.setProductId(productId);
//                    taiZangService.saveProduct(product);
//                    userAssessDetail.setProductId(productId);
//                }else{
//                    userAssessDetail.setProductId(temp_product.getProductId());
//                }
//
//
//
//                //设置detail的一些参数
//                userAssessDetail.setUserAssessDetailId(taiZangService.findUadMaxId()+1);
//                //userAssessDetail.setUserAssessRecordId();
//                userAssessDetail.setTip(tempone.getTip());
//                userAssessDetail.setUserName(tempone.getUserName());
//                userAssessDetail.setIsSecret(0);
//                userAssessDetail.setMacIp(tempone.getIp());
//
//
//
//
//                taiZangService.saveUserAssessDetail(userAssessDetail);
//                //taiZangService.sav
//
//                //product.set
//                //userAssessDetail.setResponseYhbh();
//                //userAssessDetail.setAddressId(/tempone.getRoomNum());
//
//            }
//        }
//        //tempThree
//        List<Tempthree> tempones = taiZangService.findAllTempone();
//        for(Tempthree tempone:tempones){
//            String name = tempone.getName();
//            // if()
//
//            YHB yh = yhbService.findYHBByYhmc(name);
//            DMB dmb = dmbService.findByBmmc(tempone.getDepartName());
//            if(dmb==null){
//                System.out.println("$$$$$$$$$$$$$$$$"+tempone.getDepartName());
//                continue;
//            }
//            if(yh==null){//用户不存在
//                //DMB dmb = dmbService.findByBmmc(tempone.getDepartName());
//                DepartmentAssessRecord temp_dar = taiZangService.findByObjectAndDmbh(dmb.getDmbh(),Long.valueOf(3));
//                UserAssessDetail userAssessDetail = new UserAssessDetail();
//                userAssessDetail.setIsDepartAssess(1);//设置是否为部门资产
//                //userAssessDetail.setAddressId(Long.valueOf(tempone.getRoomNum()).toString());//设置为部门房间号
//                if(temp_dar==null){
//                    Long darid = Long.valueOf(taiZangService.findDarMaxId()+1);
//                    DepartmentAssessRecord departmentAssessRecord = new DepartmentAssessRecord();
//                    departmentAssessRecord.setDepartmentAssessRecordId(darid);
//                    departmentAssessRecord.setDmbh(dmb.getDmbh());
//                    departmentAssessRecord.setObjectId(Long.valueOf(3));
//                    departmentAssessRecord.setObjectName("移动介质");
//                    departmentAssessRecord.setObjectNum(Long.valueOf(tempone.getProductNum()));
//                    taiZangService.saveDar(departmentAssessRecord);
//                    userAssessDetail.setUserAssessRecordId(darid);
//                }else{
//                    temp_dar.setObjectNum(temp_dar.getObjectNum()+Long.valueOf(tempone.getProductNum()));
//                    taiZangService.saveDar(temp_dar);
//                    userAssessDetail.setUserAssessRecordId(temp_dar.getDepartmentAssessRecordId());
//                }
//
//
//
//
//                Product temp_product = taiZangService.findProductByName(tempone.getProductType());
//                if(temp_product==null){
//                    Long productId = taiZangService.findProductMaxId()+1;
//                    Product product = new Product();
//                    product.setObjectId(Long.valueOf(3));
//                    product.setProductName(tempone.getProductType());
//                    product.setProductId(productId);
//                    taiZangService.saveProduct(product);
//                    userAssessDetail.setProductId(productId);
//                }else{
//                    userAssessDetail.setProductId(temp_product.getProductId());
//                }
//
//
//
//                //设置detail的一些参数
//                userAssessDetail.setUserAssessDetailId(taiZangService.findUadMaxId()+1);
//                //userAssessDetail.setUserAssessRecordId();
//                //userAssessDetail.setTip(tempone.getTip());
//                userAssessDetail.setUserName(tempone.getName());
//                userAssessDetail.setIsSecret(0);
//
//
//
//
//                taiZangService.saveUserAssessDetail(userAssessDetail);
//                for(int i = 0;i<Integer.valueOf(tempone.getProductNum())-1;i++){
//                    userAssessDetail.setUserAssessDetailId(taiZangService.findUadMaxId()+1);
//                    taiZangService.saveUserAssessDetail(userAssessDetail);
//                }
//                //taiZangService.sav
//
//                //product.set
//                //userAssessDetail.setResponseYhbh();
//                //userAssessDetail.setAddressId(/tempone.getRoomNum());
//
//
//            }
//            else{
//                dmb = dmbService.findByBmdm(yh.getYhbm());
//                UserAssessRecord temp_dar = taiZangService.findUarByObjectAndDmbh(Long.valueOf(yh.getYhbh()),Long.valueOf(3));
//                UserAssessDetail userAssessDetail = new UserAssessDetail();
//                userAssessDetail.setIsDepartAssess(0);//设置是否为部门资产
//                //userAssessDetail.setAddressId(Long.valueOf(tempone.getRoomNum()).toString());//设置为部门房间号
//                if(temp_dar==null){
//                    Long darid = Long.valueOf(taiZangService.findUarMaxId()+1);
//                    UserAssessRecord departmentAssessRecord = new UserAssessRecord();
//                    departmentAssessRecord.setUserAssessRecordId(darid);
//                    departmentAssessRecord.setYhbh(Long.valueOf(yh.getYhbh()));
//                    departmentAssessRecord.setObjectId(Long.valueOf(3));
//                    departmentAssessRecord.setObjectName("移动介质");
//                    departmentAssessRecord.setObjectNum(Long.valueOf(tempone.getProductNum()));
//                    taiZangService.saveUar(departmentAssessRecord);
//                    userAssessDetail.setUserAssessRecordId(darid);
//                }else{
//                    temp_dar.setObjectNum(temp_dar.getObjectNum()+Long.valueOf(tempone.getProductNum()));
//                    taiZangService.saveUar(temp_dar);
//                    userAssessDetail.setUserAssessRecordId(temp_dar.getUserAssessRecordId());
//                }
//
//
//
//
//                Product temp_product = taiZangService.findProductByName(tempone.getProductType());
//                if(temp_product==null){
//                    Long productId = taiZangService.findProductMaxId()+1;
//                    Product product = new Product();
//                    product.setObjectId(Long.valueOf(3));
//                    product.setProductName(tempone.getProductType());
//                    product.setProductId(productId);
//                    taiZangService.saveProduct(product);
//                    userAssessDetail.setProductId(productId);
//                }else{
//                    userAssessDetail.setProductId(temp_product.getProductId());
//                }
//
//
//
//                //设置detail的一些参数
//                userAssessDetail.setUserAssessDetailId(taiZangService.findUadMaxId()+1);
//                //userAssessDetail.setUserAssessRecordId();
//              //  userAssessDetail.setTip(tempone.getTip());
//                userAssessDetail.setUserName(tempone.getName());
//                userAssessDetail.setIsSecret(0);
//
//
//
//
//                taiZangService.saveUserAssessDetail(userAssessDetail);
//                for(int i = 0;i<Integer.valueOf(tempone.getProductNum())-1;i++){
//                    userAssessDetail.setUserAssessDetailId(taiZangService.findUadMaxId()+1);
//                    taiZangService.saveUserAssessDetail(userAssessDetail);
//                }
//                //taiZangService.sav
//
//                //product.set
//                //userAssessDetail.setResponseYhbh();
//                //userAssessDetail.setAddressId(/tempone.getRoomNum());
//
//            }
//        }
        //temp Two
//        List<Temptwo> tempones = taiZangService.findAllTempone();
//        for(Temptwo tempone:tempones){
//            String name = tempone.getResponser();
//           // if()
//
//            YHB yh = yhbService.findYHBByYhmc(name);
//            DMB dmb = dmbService.findByBmmc(tempone.getDepartName());
//            if(dmb==null){
//                System.out.println("$$$$$$$$$$$$$$$$"+tempone.getDepartName());
//                continue;
//            }
//            if(yh==null){//用户不存在
//                //DMB dmb = dmbService.findByBmmc(tempone.getDepartName());
//                DepartmentAssessRecord temp_dar = taiZangService.findByObjectAndDmbh(dmb.getDmbh(),Long.valueOf(1));
//                UserAssessDetail userAssessDetail = new UserAssessDetail();
//                userAssessDetail.setIsDepartAssess(1);//设置是否为部门资产
//                userAssessDetail.setAddressId(Long.valueOf(tempone.getRoomNum()).toString());//设置为部门房间号
//                if(temp_dar==null){
//                    Long darid = Long.valueOf(taiZangService.findDarMaxId()+1);
//                    DepartmentAssessRecord departmentAssessRecord = new DepartmentAssessRecord();
//                    departmentAssessRecord.setDepartmentAssessRecordId(darid);
//                    departmentAssessRecord.setDmbh(dmb.getDmbh());
//                    departmentAssessRecord.setObjectId(Long.valueOf(1));
//                    departmentAssessRecord.setObjectName("台式计算机");
//                    departmentAssessRecord.setObjectNum(Long.valueOf(1));
//                    taiZangService.saveDar(departmentAssessRecord);
//                    userAssessDetail.setUserAssessRecordId(darid);
//                }else{
//                    temp_dar.setObjectNum(temp_dar.getObjectNum()+1);
//                    taiZangService.saveDar(temp_dar);
//                    userAssessDetail.setUserAssessRecordId(temp_dar.getDepartmentAssessRecordId());
//                }
//
//                Address temp_address = taiZangService.findAddress(Long.valueOf(tempone.getRoomNum()).toString());
//                if(temp_address==null){
//                    Address address = new Address();
//                    address.setAddressId(Long.valueOf(tempone.getRoomNum()).toString());
//                    address.setAddressName(tempone.getDepartName());
//                    address.setAddressBmbh(dmb.getDmbh());
//                    taiZangService.saveAddress(address);
//                    //userAssessDetail.setAddressId();
//                }
//
//
//                Product temp_product = taiZangService.findProductByName(tempone.getProductType());
//                if(temp_product==null){
//                    Long productId = taiZangService.findProductMaxId()+1;
//                    Product product = new Product();
//                    product.setObjectId(Long.valueOf(1));
//                    product.setProductName(tempone.getProductType());
//                    product.setProductId(productId);
//                    taiZangService.saveProduct(product);
//                    userAssessDetail.setProductId(productId);
//                }else{
//                    userAssessDetail.setProductId(temp_product.getProductId());
//                }
//
//
//
//                //设置detail的一些参数
//                userAssessDetail.setUserAssessDetailId(taiZangService.findUadMaxId()+1);
//                //userAssessDetail.setUserAssessRecordId();
//                userAssessDetail.setTip(tempone.getTip());
//                userAssessDetail.setUserName(tempone.getResponser());
//                userAssessDetail.setIsSecret(1);
//
//
//
//
//                taiZangService.saveUserAssessDetail(userAssessDetail);
//                //taiZangService.sav
//
//                //product.set
//                //userAssessDetail.setResponseYhbh();
//                //userAssessDetail.setAddressId(/tempone.getRoomNum());
//
//
//            }
//            else{
//                //DMB dmb = dmbService.findByBmmc(tempone.getDepartName());
//                UserAssessRecord temp_dar = taiZangService.findUarByObjectAndDmbh(Long.valueOf(yh.getYhbh()),Long.valueOf(1));
//                UserAssessDetail userAssessDetail = new UserAssessDetail();
//                userAssessDetail.setIsDepartAssess(0);//设置是否为部门资产
//                userAssessDetail.setAddressId(Long.valueOf(tempone.getRoomNum()).toString());//设置为部门房间号
//                if(temp_dar==null){
//                    Long darid = Long.valueOf(taiZangService.findUarMaxId()+1);
//                    UserAssessRecord departmentAssessRecord = new UserAssessRecord();
//                    departmentAssessRecord.setUserAssessRecordId(darid);
//                    departmentAssessRecord.setYhbh(Long.valueOf(yh.getYhbh()));
//                    departmentAssessRecord.setObjectId(Long.valueOf(1));
//                    departmentAssessRecord.setObjectName("台式计算机");
//                    departmentAssessRecord.setObjectNum(Long.valueOf(1));
//                    taiZangService.saveUar(departmentAssessRecord);
//                    userAssessDetail.setUserAssessRecordId(darid);
//                }else{
//                    temp_dar.setObjectNum(temp_dar.getObjectNum()+1);
//                    taiZangService.saveUar(temp_dar);
//                    userAssessDetail.setUserAssessRecordId(temp_dar.getUserAssessRecordId());
//                }
//
//                Address temp_address = taiZangService.findAddress(Long.valueOf(tempone.getRoomNum()).toString());
//                if(temp_address==null){
//                    Address address = new Address();
//                    address.setAddressId(Long.valueOf(tempone.getRoomNum()).toString());
//                    address.setAddressName(tempone.getDepartName());
//                    address.setAddressBmbh(dmb.getDmbh());
//                    taiZangService.saveAddress(address);
//                    //userAssessDetail.setAddressId();
//                }
//
//
//                Product temp_product = taiZangService.findProductByName(tempone.getProductType());
//                if(temp_product==null){
//                    Long productId = taiZangService.findProductMaxId()+1;
//                    Product product = new Product();
//                    product.setObjectId(Long.valueOf(1));
//                    product.setProductName(tempone.getProductType());
//                    product.setProductId(productId);
//                    taiZangService.saveProduct(product);
//                    userAssessDetail.setProductId(productId);
//                }else{
//                    userAssessDetail.setProductId(temp_product.getProductId());
//                }
//
//
//
//                //设置detail的一些参数
//                userAssessDetail.setUserAssessDetailId(taiZangService.findUadMaxId()+1);
//                //userAssessDetail.setUserAssessRecordId();
//                userAssessDetail.setTip(tempone.getTip());
//                userAssessDetail.setUserName(tempone.getResponser());
//                userAssessDetail.setIsSecret(1);
//
//
//
//
//                taiZangService.saveUserAssessDetail(userAssessDetail);
//                //taiZangService.sav
//
//                //product.set
//                //userAssessDetail.setResponseYhbh();
//                //userAssessDetail.setAddressId(/tempone.getRoomNum());
//
//            }
//        }

        return new HashMap<>();
    }
}
