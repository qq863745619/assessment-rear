package com.nju.software.assessment.service;

import com.nju.software.assessment.bean.*;
import com.nju.software.assessment.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaiZangService {
    @Autowired
    UserAssessRecordDao userAssessRecordDao;

    @Autowired
    UserAssessDetailDao userAssessDetailDao;

    @Autowired
    ObjectDao objectDao;

    @Autowired
    ProductDao productDao;

    @Autowired
    PubXtglYhbDao pubXtglYhbDao;

    @Autowired
    PubDmbDao pubDmbDao;

    @Autowired
    TempOneDao tempOneDao;

    @Autowired
    DepartAssessRecordDao departAssessRecordDao;

    @Autowired
    AddressDao addressDao;


    public List<UserAssessRecord> findUarSecCom(List<Long> objectsId) {
        return userAssessRecordDao.findUarSecCom(objectsId);
    }

    public List<UserAssessDetail> findByRecordIdList(List<Long> uarIdList,Integer isSecret) {
      return userAssessDetailDao.findByRecordIdList(uarIdList,isSecret);

    }

    public List<UserAssessRecord> findUarByYhbhList(List<Integer> list,List<Long> objectsId) {
        return userAssessRecordDao.findUarByYhbhList(list,objectsId);
    }

    public List<Long> findAllComputer() {
        return objectDao.findAllComputer();
    }

    public List<Long> findAllAutoDevice() {
        return objectDao.findAllAutoDevice();
    }

    public String findObjectNameByProductId(Long productId) {
        return productDao.findObjectNameByProductId(productId);
    }

    public String findProductNameByProductId(Long productId) {
        return productDao.findProductNameByProductId(productId);
    }

    public void savePubXtglYhbList(List<PubXtglYhb> pubXtglYhbs) {
        pubXtglYhbDao.saveAll(pubXtglYhbs);
    }

    public void savePubDmb(List<PubDmb> pubDmbs) {
        pubDmbDao.saveAll(pubDmbs);
    }

    public List<Tempsix> findAllTempone() {
        return tempOneDao.findAll();
    }

    public int findDarMaxId() {
        return departAssessRecordDao.findMaxId();
    }

    public void saveDar(DepartmentAssessRecord departmentAssessRecord) {
        departAssessRecordDao.save(departmentAssessRecord);
    }

    public void saveAddress(Address address) {
        addressDao.save(address);
    }

    public Long findUadMaxId() {
        return userAssessDetailDao.findUadMaxId();
    }

    public Long findProductMaxId() {
        return productDao.findProductMaxId();
    }

    public void saveProduct(Product product) {
        productDao.save(product);
    }

    public void saveUserAssessDetail(UserAssessDetail userAssessDetail) {
        userAssessDetailDao.save(userAssessDetail);
    }

    public Integer findUarMaxId() {
        return userAssessRecordDao.findMaxUserAssessRecordId();
    }

    public void saveUar(UserAssessRecord userAssessRecord) {
        userAssessRecordDao.save(userAssessRecord);
    }

    public DepartmentAssessRecord findByObjectAndDmbh(String dmbh,Long objectId) {
        return departAssessRecordDao.findByObjectAndDmbh(dmbh,objectId);
    }

    public Address findAddress(String roomNum) {
        return addressDao.findAddress(roomNum);
    }

    public Product findProductByName(String productType) {
        List<Product> list =  productDao.findByName(productType);
        if(list.size()>0){
            return list.get(0);
        }
       return null;
    }

    public UserAssessRecord findUarByObjectAndDmbh(Long yhbh, Long i) {
        return userAssessRecordDao.findUarByObjectAndDmbh(yhbh,i);
    }


    public List<UserAssessDetail> findAllUarWillDistribute() {
        return userAssessDetailDao.findAllUarWillDistribute();
    }

    public String findDepartNameByUadId(Long userAssessRecordId, Integer isDepartAssess) {
        if(isDepartAssess==1){
            DepartmentAssessRecord departmentAssessRecord = departAssessRecordDao.findById(userAssessRecordId).get();
            return pubDmbDao.findById(departmentAssessRecord.getDmbh()).get().getDmms();
        }
        else {
            return "非部门资产";
        }
    }

    public void updateChangeUadStatus(Long id) {
        userAssessDetailDao.updateChangeUadStatus(id);
    }



    public List<UserAssessDetail> findAllUarWillDistributeDivide() {
        return userAssessDetailDao.findAllUarWillDistributeDivide();
    }

    public void updateChangeUadStatusDivide(Long id) {
        userAssessDetailDao.updateChangeUadStatusDivide(id);
    }

    public List<UserAssessRecord> findUarByYhbh(Long yhbh) {
        return userAssessRecordDao.findByYhbh(yhbh);
    }

    public List<Long> findUarIdByYhbh(Long yhbh) {
        return userAssessRecordDao.findUarIdByYhbh(yhbh);
    }

    public List<UserAssessDetail> findUadByUarList(List<Long> userAssessRecordIdList) {
        return userAssessDetailDao.findUadByUarList(userAssessRecordIdList);
    }

    public List<Long> finddarIdBydmbh(Long dmbh) {
        return departAssessRecordDao.findUadIdBydmbh(dmbh);
    }

    public List<UserAssessDetail> findUadBydarList(List<Long> userAssessRecordIdList) {
        return userAssessDetailDao.findUadBydarList(userAssessRecordIdList);
    }
}
