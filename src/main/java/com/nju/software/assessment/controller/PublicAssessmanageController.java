package com.nju.software.assessment.controller;



import com.nju.software.assessment.bean.*;
import com.nju.software.assessment.bean2.DMB;
import com.nju.software.assessment.bean2.YHB;
import com.nju.software.assessment.dao.UserAuthorityRecordDao;
import com.nju.software.assessment.model.*;
import com.nju.software.assessment.service.*;
import com.nju.software.assessment.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.Object;
import java.util.*;

@Controller
public class PublicAssessmanageController {
    @Autowired
    PublicAssessManageService publicAssessManageService;

    @Autowired
    YhbService yhbService;

    @Autowired
    DmbService dmbService;

    @Autowired
    AuthorityService authorityService;

    @Autowired
    TaiZangService taiZangService;

    @ResponseBody
    @RequestMapping(value = "/addObject",method = RequestMethod.POST)
    public Map addObject(com.nju.software.assessment.bean.Object object ){//删除授予的权限
        //System.out.println(obj);

        int maxObjectId = publicAssessManageService.findMaxObjectId();

        object.setObjectId(Long.valueOf(maxObjectId+1));
        int code = 200;
        Map map = new HashMap();
        try{
            //authorityService.saveAuthority(auth);
            publicAssessManageService.saveObject(object);
            //peopleConfigDao.save(peopleConfig);
        }catch (Exception e){
            code = -1;
        }
        map.put("code",code);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/addDistributionRecord",method = RequestMethod.POST)
    public Map<String,Object> addDistributionRecord(DistributionListModel distributionListModel){
        //AssessDistribution assessDistribution = new AssessDistribution();
        //UUID uuid =  new UUID()

        System.out.println("哈哈哈");
        int code = 200;
        Map map = new HashMap();
        try {
            UUID uuid = new UUID();
            for (List<String> yhbhList : distributionListModel.getYhbhList()) {

                String yhbh = yhbhList.get(1);
                //存储分发记录
                String uid = uuid.getUUID();
                AssessDistribution assessDistribution = new AssessDistribution();
                assessDistribution.setYhbh(Integer.valueOf(yhbh));
                assessDistribution.setDistributionId(uid);
                assessDistribution.setDistributionTime(new Date());
                assessDistribution.setDistributionState(1);
                publicAssessManageService.saveAssessDistribution(assessDistribution);
                for (int i = 0; i < distributionListModel.getKeyList().size(); i++) {
                    //存储分发细节

                    String subuid = uuid.getUUID();
                    DistributionDetail distributionDetail = new DistributionDetail();
                    distributionDetail.setDistributionDetailId(subuid);
                    distributionDetail.setObjectId(Long.valueOf(distributionListModel.getKeyList().get(i)));
                    distributionDetail.setDistributionId(uid);
                    distributionDetail.setObjectNum(Integer.valueOf(distributionListModel.getAmountList().get(i)));

                    publicAssessManageService.saveDistributionDetail(distributionDetail);

                    //修改用户物品数量
                    UserAssessRecord userAssessRecord = publicAssessManageService.findAssessRecordByYhbhAndObjectId(Long.valueOf(yhbh), Long.valueOf(distributionListModel.getKeyList().get(i)));
                    if(userAssessRecord!=null){
                        System.out.println(userAssessRecord);

                        System.out.println(userAssessRecord.getObjectNum());
                        System.out.println(Integer.valueOf(distributionListModel.getAmountList().get(i)));
                        userAssessRecord.setObjectNum(userAssessRecord.getObjectNum() + Integer.valueOf(distributionListModel.getAmountList().get(i)));
                        System.out.println(userAssessRecord.getObjectNum());
                        publicAssessManageService.saveUserAssessRecord(userAssessRecord);
                    }
                    else{
                        UserAssessRecord userAssessRecord1 = new UserAssessRecord();
                        int maxid = publicAssessManageService.findMaxUserAssessRecordId();
                        userAssessRecord1.setUserAssessRecordId(Long.valueOf(maxid+1));
                        userAssessRecord1.setObjectNum(Long.valueOf(distributionListModel.getAmountList().get(i)));
                        userAssessRecord1.setYhbh(Long.valueOf(yhbh));
                        userAssessRecord1.setObjectName(distributionListModel.getValueList().get(i));
                        userAssessRecord1.setObjectId(Long.valueOf(distributionListModel.getKeyList().get(i)));

                        publicAssessManageService.saveUserAssessRecord(userAssessRecord1);
                    }
                }
            }
        }
        catch (Exception e) {
            code = -1;
        }
        map.put("code",code);
        return map;
    }
    @ResponseBody
    @RequestMapping(value = "/getDepartAssessUser")
    public Map<String,Object> getDepartAssessUser(String yhbh){
        List<UserAssessRecord> recordList = publicAssessManageService.getAssessRecordListByYhbh(yhbh);

        Map<String,Object> sMap = new HashMap<>();
        sMap.put("list",recordList);

        return sMap;
    }

    @ResponseBody
    @RequestMapping(value = "/getDepartAssessUserAgain")
    public Map<String,Object> getDepartAssessUserAgain(String yhbh){
        List<UserAssessRecord> recordList = publicAssessManageService.getAssessRecordListByYhbh(yhbh);

        List<com.nju.software.assessment.bean.Object> objects = publicAssessManageService.findAllObject();
        Map<String,Object> sMap = new HashMap<>();
        sMap.put("list",recordList);

        sMap.put("objects",objects);

        return sMap;
    }

    @ResponseBody
    @RequestMapping(value = "/getUserAndUserAssessDetail")
    public Map<String,Object> getUserAndUserAssessDetail(String yhbh){
        YHB user = yhbService.findByYhbh(Integer.valueOf(yhbh));

        DMB dmb = dmbService.findByBmdm(user.getYhbm());
        List<UserAssessRecord> userAssessRecordList  = publicAssessManageService.findUserAssessRecordByYhbh(Long.valueOf(yhbh));


        List<UserAssessDetail> userAssessDetails = new ArrayList<UserAssessDetail>();
        for(UserAssessRecord userAssessRecord:userAssessRecordList){
            List<UserAssessDetail> temp_list = publicAssessManageService.getUserAssessDetailByUard(userAssessRecord.getUserAssessRecordId());
            if(temp_list!=null){userAssessDetails.addAll(temp_list);}
        }

        List<UadProductModel> uadProductModelList = new ArrayList<>();
        for(UserAssessDetail userAssessDetail:userAssessDetails){
            Product product = publicAssessManageService.getProductById(userAssessDetail.getProductId());
            if(null != product) {
                //System.out.println("哈哈哈哈"+product.getProductName());
                uadProductModelList.add(new UadProductModel(userAssessDetail,product));
            }

        }

        Map<String,Object> smap = new HashMap<>();
        smap.put("user",user);
        smap.put("uad",uadProductModelList);
        smap.put("dep",dmb);
        return smap;

    }

    //根据用户编号获取分配记录
    @ResponseBody
    @RequestMapping(value = "/getUserDistributionDetail")
    public Map<String,Object> getUserDistributionDetail(String yhbh,Integer objectId){
        //List<UserAssessRecord> recordList = publicAssessManageService.getAssessRecordListByYhbh(yhbh);

       // System.out.println("=========="+yhbh);
       // System.out.println("********"+objectId);
        List<AssessDistribution> adList = publicAssessManageService.getAssessDistributionByYhbh(Integer.valueOf(yhbh));
//tring distributionId, Integer distributionState, Date distributionTime, String distributionDetailId, Long objectId, Integer objectNum) {
        List<DistributionDetailModel> dbdmList = new ArrayList<DistributionDetailModel>();
        for(AssessDistribution ad:adList) {
            List<DistributionDetail> dbdList = publicAssessManageService.getDistributionDetailByDistIdAndObjName(ad.getDistributionId(),objectId);
            for(DistributionDetail dbd: dbdList) {
                DistributionDetailModel distributionDetailModel = new DistributionDetailModel(
                        ad.getDistributionId(),
                        ad.getDistributionState(),
                        ad.getDistributionTime(),
                        dbd.getDistributionDetailId(),
                        dbd.getObjectId(),
                        dbd.getObjectNum()

                );
                dbdmList.add(distributionDetailModel);
            }
        }
        Map<String,Object> sMap = new HashMap<>();
        sMap.put("list",dbdmList);

        return sMap;
    }

    @ResponseBody
    @RequestMapping(value = "/getObjectList")
    public Map<String,Object> getObjectList(){
       List<com.nju.software.assessment.bean.Object> objects = publicAssessManageService.findAllObject();

        Map<String,Object> sMap = new HashMap<>();
        sMap.put("list",objects);

        return sMap;
    }
    @ResponseBody
    @RequestMapping(value="/getObjectListNoFixed")
    public Map<String,Object> getObjectListNoFixed(){
        List<com.nju.software.assessment.bean.Object> objects = publicAssessManageService.findAllObject();


        Map<String,Object> sMap = new HashMap<>();
        sMap.put("list",objects);

        return sMap;
    }



    @ResponseBody
    @RequestMapping(value = "/getUserAssessRecord")
    public Map<String,Object> getUserAssessRecord(){
        List<UserAssessRecord> recordList = publicAssessManageService.findAllUserAssessRecord();

        Map<String,Object> sMap = new HashMap<>();
        sMap.put("list",recordList);

        return sMap;
    }


    @ResponseBody
    @RequestMapping(value = "/deleteAllRecord")
    public Map<String,Object> deleteAllRecord(String yhbh){
        //删除分配记录
      /*    List<AssessDistribution> adlist = publicAssessManageService.getAssessDistributionByYhbh(Integer.valueOf(yhbh));

        for(AssessDistribution assessDistribution: adlist) {
          //  publicAssessManageService.deleteDistributionDetailByAssessDistributionId(assessDistribution.getDistributionId());
        }

     */
      System.out.println("____"+yhbh);
        Map<String,Object> sMap = new HashMap<>();


          //删除分配记录
          List<AssessDistribution> adlist = publicAssessManageService.getAssessDistributionByYhbh(Integer.valueOf(yhbh));
          for(AssessDistribution assessDistribution: adlist) {
                publicAssessManageService.deleteDistributionDetailByAssessDistributionId(assessDistribution.getDistributionId());
          }
          publicAssessManageService.deleteAssessDistributionList(adlist);


          //删除库存记录
          List<UserAssessRecord> uar = publicAssessManageService.getAssessRecordListByYhbh(yhbh);
          for(UserAssessRecord userAssessRecord:uar){
              publicAssessManageService.deleteUserAssessDetailByUard(userAssessRecord.getUserAssessRecordId());
          }
          publicAssessManageService.deleteUserAssessRecordByYhbh(Long.valueOf(yhbh));

          sMap.put("code",200);






        return sMap;
    }

    @ResponseBody
    @RequestMapping(value = "/getUserAssessDetailByUard")
    public Map<String,Object> getUserAssessDetailByUard(String Uard){
        //List<UserAssessRecord> recordList = publicAssessManageService.getAssessRecordListByYhbh(yhbh);

        List<UserAssessDetail> uadList = publicAssessManageService.getUserAssessDetailByUard(Long.valueOf(Uard));

        List<UadProductModel> uadProductModels = new ArrayList<UadProductModel>();
        for(UserAssessDetail userAssessDetail:uadList){
            Product product = publicAssessManageService.getProductById(userAssessDetail.getProductId());
            YHB yhb = yhbService.findByYhbh(userAssessDetail.getResponseYhbh());
            if(null != product&& null!=yhb) {
                //System.out.println("哈哈哈哈"+product.getProductName());
                uadProductModels.add(new UadProductModel(userAssessDetail,product,yhb));
            }



        }
        //System.out.println("哈哈哈哈"+uadLi);
        //List<com.nju.software.assessment.bean.Object> objects = publicAssessManageService.findAllObject();
        Map<String,Object> sMap = new HashMap<>();
        sMap.put("list",uadProductModels);

       // sMap.put("objects",objects);

        return sMap;
    }

    @ResponseBody
    @RequestMapping(value = "/getLocation")
    public Map<String,Object> getLocation(String userAssessRecordId){
        List<UserAssessDetail> uadList = publicAssessManageService.getUserAssessDetailByUard(Long.valueOf(userAssessRecordId));



        List<UadProductModel> uadProductModels = new ArrayList<UadProductModel>();
        for(UserAssessDetail userAssessDetail:uadList){
            Product product = publicAssessManageService.getProductById(userAssessDetail.getProductId());
            if(null != product) {
                //System.out.println("哈哈哈哈"+product.getProductName());
                uadProductModels.add(new UadProductModel(userAssessDetail,product));
            }

        }
        HashSet<String> adrSet = new HashSet<String>();
        for(UserAssessDetail userAssessDetail:uadList){
            adrSet.add(userAssessDetail.getAddressId());
        }



        List<Address> addressList = new ArrayList<Address>();

        for(String adid: adrSet){
            addressList.add(publicAssessManageService.findAddressById(adid));
        }
        Map<String,Object> sMap = new HashMap<>();
        sMap.put("adrlist",addressList);
        sMap.put("delist",uadProductModels);

        // sMap.put("objects",objects);

        return sMap;
    }

    @ResponseBody
    @RequestMapping(value="/getUserAssessDetailByYhbhAndObjectId")
    public Map<String,Object> getUserAssessDetailByYhbhAndObjectId(String yhbh,String objectId){
        UserAssessRecord userAssessRecord = publicAssessManageService.findAssessRecordByYhbhAndObjectId(Long.valueOf(yhbh),Long.valueOf(objectId));

        Map<String,Object> smap = new HashMap<>();
        if(null != userAssessRecord) {
            List<UserAssessDetail> userAssessDetails = publicAssessManageService.getUserAssessDetailByUard(userAssessRecord.getUserAssessRecordId());


            List<UadProductModel> uadProductModels = new ArrayList<UadProductModel>();
            for(UserAssessDetail userAssessDetail:userAssessDetails){
                Product product = publicAssessManageService.getProductById(userAssessDetail.getProductId());
                if(null != product) {
                    //System.out.println("哈哈哈哈"+product.getProductName());
                    uadProductModels.add(new UadProductModel(userAssessDetail,product));
                }

            }

            smap.put("uadlist",uadProductModels);
        }

        return smap;
    }
    @ResponseBody
    @RequestMapping(value = "/getProductList")
    public Map<String,Object> getProductList(String objectId){
        List<Product>products =  publicAssessManageService.findAllProductByObjectId(Long.valueOf(objectId));

        Map<String,Object> smap = new HashMap<>();

        smap.put("productList",products);
        return  smap;
    }

    @ResponseBody
    @RequestMapping(value = "/getAuthorityRecordList")
    public Map<String,Object> getAuthorityRecordList(){

        List<UserAuthorityRecord> uarList = authorityService.findAllAuthorityRecord();
        Map<String,Object> smap = new HashMap<>();


        List<UserAuthorityInfoModel> list = new ArrayList<UserAuthorityInfoModel>();

        for(UserAuthorityRecord userAuthorityRecord:uarList) {
            Authority authority = authorityService.findByAuthorityId(userAuthorityRecord.getQxbh());
            YHB yhb = yhbService.findByYhbh(userAuthorityRecord.getYhbh().intValue());
            DMB dmb = dmbService.findByBmdm(yhb.getYhbm());
            UserAuthorityInfoModel userAuthorityInfoModel = new UserAuthorityInfoModel(authority,yhb,dmb,userAuthorityRecord.getYhqxbh());
            list.add(userAuthorityInfoModel);
           // System.out.println("{{{{{{{"+userAuthorityInfoModel.get);
        }


        smap.put("recordList",list);
        return  smap;
    }
    @ResponseBody
    @RequestMapping(value = "/getLedger")
    public Map<String,Object> getLedger(){
        List<Ledger> ledgers = publicAssessManageService.findAllLedger();
        Map<String,Object> map =new HashMap<>();
        map.put("ledgerList",ledgers);
        return map;
    }
    @ResponseBody
    @RequestMapping(value = "/getWillDistribute")
    public Map<String,Object> getWillDistribute(){
        List<UserAssessDetail >userAssessDetails = taiZangService.findAllUarWillDistribute();
        List<UadExtModel> uadExtModels = new ArrayList<UadExtModel>();

        for(UserAssessDetail userAssessDetail:  userAssessDetails){
            UadExtModel uadExtModel = new UadExtModel(userAssessDetail,taiZangService.findProductNameByProductId(Long.valueOf(userAssessDetail.getProductId())),taiZangService.findDepartNameByUadId(userAssessDetail.getUserAssessRecordId(),userAssessDetail.getIsDepartAssess()));
            uadExtModels.add(uadExtModel);
        }
        Map<String,Object> map = new HashMap<>();
        map.put("data",uadExtModels);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/changeUadStatus")
    public Map<String,Object> changeUadStatus(Long id){
        taiZangService.updateChangeUadStatus(id);
        Map<String,Object> map = new HashMap<>();
        map.put("code",200);
        return  map;
    }
    @ResponseBody
    @RequestMapping(value = "/changeUadStatusDivide")
    public Map<String,Object> changeUadStatusDivide(Long id){
        taiZangService.updateChangeUadStatusDivide(id);
        Map<String,Object> map = new HashMap<>();
        map.put("code",200);
        return  map;
    }
    @ResponseBody
    @RequestMapping(value = "/getWillDistributeDivide")
    public Map<String,Object> getWillDistributeDivide(){
        List<UserAssessDetail >userAssessDetails = taiZangService.findAllUarWillDistributeDivide();
        List<UadExtModel> uadExtModels = new ArrayList<UadExtModel>();

        for(UserAssessDetail userAssessDetail:  userAssessDetails){
            UadExtModel uadExtModel = new UadExtModel(userAssessDetail,taiZangService.findProductNameByProductId(Long.valueOf(userAssessDetail.getProductId())),taiZangService.findDepartNameByUadId(userAssessDetail.getUserAssessRecordId(),userAssessDetail.getIsDepartAssess()));
            uadExtModels.add(uadExtModel);
        }
        Map<String,Object> map = new HashMap<>();
        map.put("data",uadExtModels);
        return map;
    }
    @ResponseBody
    @RequestMapping(value = "/distributeObject",method = RequestMethod.POST)
    public Map<String,Object> distributeObject(@RequestBody  DisTributeModel distributionModel){
        Long uarId;
        if(distributionModel.getIsDepartAssess()==1){//部门资产
            DepartmentAssessRecord departmentAssessRecord = taiZangService.findByObjectAndDmbh(distributionModel.getDmbh(),distributionModel.getObjectId());
            if(departmentAssessRecord==null){

                departmentAssessRecord = new DepartmentAssessRecord();
                departmentAssessRecord.setDepartmentAssessRecordId(Long.valueOf(taiZangService.findDarMaxId()+1));
                //uarId = departmentAssessRecord.getDepartmentAssessRecordId();
                departmentAssessRecord.setObjectNum(Long.valueOf(1));
                departmentAssessRecord.setObjectId(distributionModel.getObjectId());
                departmentAssessRecord.setObjectName(distributionModel.getObjectName());
                departmentAssessRecord.setDmbh(distributionModel.getDmbh());
            }else{

                departmentAssessRecord.setObjectNum(departmentAssessRecord.getObjectNum()+1);
            }
            uarId = departmentAssessRecord.getDepartmentAssessRecordId();
            taiZangService.saveDar(departmentAssessRecord);
        }
        else{//个人资产
            UserAssessRecord userAssessRecord = taiZangService.findUarByObjectAndDmbh(Long.valueOf(distributionModel.getResponseYhbh()),distributionModel.getObjectId());
            if(userAssessRecord==null){

                userAssessRecord = new UserAssessRecord();
                userAssessRecord.setUserAssessRecordId(Long.valueOf(taiZangService.findUarMaxId()+1));
                //uarId = userAssessRecord.getUserAssessRecordId();
                userAssessRecord.setYhbh(Long.valueOf(distributionModel.getResponseYhbh()));
                userAssessRecord.setObjectId(distributionModel.getObjectId());
                userAssessRecord.setObjectName(distributionModel.getObjectName());
                userAssessRecord.setObjectNum(Long.valueOf(1));
            }else{
                userAssessRecord.setObjectNum(userAssessRecord.getObjectNum()+1);

            }
            uarId = userAssessRecord.getUserAssessRecordId();
            taiZangService.saveUar(userAssessRecord);
        }

        //分配具体资产
        UserAssessDetail userAssessDetail = new UserAssessDetail();
        userAssessDetail.setUserAssessDetailId(taiZangService.findUadMaxId()+1);
        userAssessDetail.setUse(distributionModel.getUse());
        userAssessDetail.setProductId(distributionModel.getProductId());
        userAssessDetail.setMacIp(distributionModel.getMacIp());
        userAssessDetail.setIsSecret(distributionModel.getIsSecret());
        userAssessDetail.setUserName(distributionModel.getUserName());
        //userAssessDetail.setTip();
        userAssessDetail.setUserAssessRecordId(uarId);
        userAssessDetail.setAddressId(distributionModel.getAddressId());
        userAssessDetail.setIsDepartAssess(distributionModel.getIsDepartAssess());
        userAssessDetail.setDeviceIndex(distributionModel.getDeviceIndex());
        userAssessDetail.setResponseYhbh(distributionModel.getResponseYhbh());
        userAssessDetail.setDecideSecretDate(distributionModel.getDecideSecretDate());
        userAssessDetail.setDiskIndex(distributionModel.getDiskIndex());
        userAssessDetail.setIsOut(distributionModel.getIsOut());
        userAssessDetail.setNetworkCondition(distributionModel.getNetworkCondition());
        userAssessDetail.setOprateSystemTime(distributionModel.getOprateSystemTime());
        //userAssessDetail.setSecretIsOut();
        userAssessDetail.setSecretLevel(distributionModel.getSecretLevel());
        userAssessDetail.setUniformNumbers(distributionModel.getUniformNumbers());
        userAssessDetail.setUseCondition(distributionModel.getUseCondition());
        userAssessDetail.setDistributeTime(new Date());
        taiZangService.saveUserAssessDetail(userAssessDetail);
       // userAssessDetail.set
        //userAssessDetail.setUseT(distributionModel.getUseT());
        //System.out.println(distributionModel.getDiskIndex());
        //System.out.println("hhhhhhhdsadsadsa");
        Map<String,Object> map = new HashMap<>();
        map.put("code",200);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/getSelfInfo")
    public Map<String,Object> getSelfInfo(Long yhbh){
        List<Long> userAssessRecordIdList = taiZangService.findUarIdByYhbh(yhbh);

        List<UserAssessDetail> userAssessDetails = taiZangService.findUadByUarList(userAssessRecordIdList);


        //List<UserAssessDetail >userAssessDetails = taiZangService.findAllUar();
        List<UadExtModel> uadExtModels = new ArrayList<UadExtModel>();

        for(UserAssessDetail userAssessDetail:  userAssessDetails){
            UadExtModel uadExtModel = new UadExtModel(userAssessDetail,taiZangService.findProductNameByProductId(Long.valueOf(userAssessDetail.getProductId())),taiZangService.findDepartNameByUadId(userAssessDetail.getUserAssessRecordId(),userAssessDetail.getIsDepartAssess()),taiZangService.findObjectNameByProductId(Long.valueOf(userAssessDetail.getProductId())));
            uadExtModels.add(uadExtModel);
        }
        Map<String,Object> map = new HashMap<>();
        map.put("data",uadExtModels);
        return  map;
    }

    @ResponseBody
    @RequestMapping(value = "/getDepartInfo")
    public Map<String,Object> getDepartInfo(Long dmbh){
        List<Long> userAssessRecordIdList = taiZangService.finddarIdBydmbh(dmbh);

        List<UserAssessDetail> userAssessDetails = taiZangService.findUadBydarList(userAssessRecordIdList);


        //List<UserAssessDetail >userAssessDetails = taiZangService.findAllUar();
        List<UadExtModel> uadExtModels = new ArrayList<UadExtModel>();

        for(UserAssessDetail userAssessDetail:  userAssessDetails){
            UadExtModel uadExtModel = new UadExtModel(userAssessDetail,taiZangService.findProductNameByProductId(Long.valueOf(userAssessDetail.getProductId())),taiZangService.findDepartNameByUadId(userAssessDetail.getUserAssessRecordId(),userAssessDetail.getIsDepartAssess()),taiZangService.findObjectNameByProductId(Long.valueOf(userAssessDetail.getProductId())));
            uadExtModels.add(uadExtModel);
        }
        Map<String,Object> map = new HashMap<>();
        map.put("data",uadExtModels);
        return  map;
    }
}
