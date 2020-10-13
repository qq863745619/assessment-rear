package com.nju.software.assessment.service;

import com.nju.software.assessment.bean.*;
import com.nju.software.assessment.bean.Object;
import com.nju.software.assessment.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicAssessManageService {
    @Autowired
    private UserAssessRecordDao userAssessRecordDao;

    @Autowired
    private ObjectDao objectDao;

    @Autowired
    private AssessDistributionDao assessDistributionDao;

    @Autowired
    private DistributionDetailDao distributionDetailDao;

    @Autowired
    private UserAssessDetailDao userAssessDetailDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private AddressDao addressDao;

    @Autowired
    private LedgerDao ledgerDao;

    public List<UserAssessRecord> getAssessRecordListByYhbh(String yhbh) {
        return userAssessRecordDao.getAssessRecordListByYhbh(yhbh);
    }

    public List<UserAssessRecord> findAllUserAssessRecord() {
        return userAssessRecordDao.findAll();
    }

    public List<Object> findAllObject() {
        return objectDao.findAll();
    }

    public void saveAllUserAssessRecord(List<UserAssessRecord> uarlist) {
        userAssessRecordDao.saveAll(uarlist);
    }


    public void saveAllAssessDistribution(List<AssessDistribution> adlist) {
        assessDistributionDao.saveAll(adlist);
    }

    public void saveAllDistributionDetail(List<DistributionDetail> dbdlist) {
        distributionDetailDao.saveAll(dbdlist);
    }

    public List<AssessDistribution> getAssessDistributionByYhbh(Integer yhbh) {
        return assessDistributionDao.getAssessDistributionByYhbh(yhbh);
    }

    public List<DistributionDetail> getDistributionDetailByDistIdAndObjName(String distributionId, Integer objectId) {
        return distributionDetailDao.findByDidAndObjName(distributionId,Long.valueOf(objectId));
    }

    public int findMaxObjectId() {
        return objectDao.findMaxId();
    }

    public void saveObject(Object object) {
        objectDao.save(object);
    }

    public UserAssessRecord findAssessRecordByYhbhAndObjectId(Long yhbh, Long objectId) {
        List<UserAssessRecord> userAssessRecordList = userAssessRecordDao.findAssessRecordByYhbhAndObjectId(yhbh, objectId);
        if(userAssessRecordList.size()>0) {
            return userAssessRecordList.get(0);
        }
        return null;
    }

    public void saveUserAssessRecord(UserAssessRecord userAssessRecord) {
        userAssessRecordDao.save(userAssessRecord);
    }

    public void saveAssessDistribution(AssessDistribution assessDistribution) {
        assessDistributionDao.save(assessDistribution);
    }

    public void saveDistributionDetail(DistributionDetail distributionDetail) {
        distributionDetailDao.save(distributionDetail);
    }

    public int findMaxUserAssessRecordId() {
        return userAssessRecordDao.findMaxUserAssessRecordId();
    }

    public void deleteDistributionDetailByAssessDistributionId(String distributionId) {
         distributionDetailDao.deleteDistributionDetailByAssessDistributionId(distributionId);
    }

    public List<UserAssessDetail> getUserAssessDetailByUard(Long Uard) {
        //UserA
       return  userAssessDetailDao.getUserAssessDetailByUard(Uard);
    }

    public Product getProductById(Long productId) {
        return productDao.findById(productId).get();
    }

    public Address findAddressById(String adid) {
        return  addressDao.findById(adid).get();
    }

    public List<UserAssessRecord> findUserAssessRecordByYhbh(Long yhbh) {
        return userAssessRecordDao.findByYhbh(yhbh);
    }

    public void deleteAssessDistributionList(List<AssessDistribution> adlist) {
        assessDistributionDao.deleteAll(adlist);
    }

    public void deleteUserAssessDetailByUard(Long userAssessRecordId) {
        userAssessDetailDao.deleteUserAssessDetailByUard(userAssessRecordId);
    }

    public void deleteUserAssessRecordByYhbh(Long yhbh) {
        userAssessRecordDao.deleteUserAssessRecordByYhbh(yhbh);
    }

    public List<Product> findAllProductByObjectId(Long objectId) {
        return  productDao.findByObjectId(objectId);
    }

    public List<Ledger> findAllLedger() {
        return ledgerDao.findAll();
    }
}
