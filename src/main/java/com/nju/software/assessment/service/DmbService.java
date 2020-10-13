package com.nju.software.assessment.service;

import com.nju.software.assessment.bean2.DMB;
import com.nju.software.assessment.dao2.DmbDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DmbService {

    @Autowired
    DmbDao dmbDao;

    public List<DMB> findAll(){
//        return dmbDao.findAll();
        return dmbDao.getDMBByLbbh("USR206-99");
    }

    public void saveAll(List<DMB> dmlist) {
        dmbDao.saveAll(dmlist);
    }


    public DMB findByBmmc(String depton) {
        List<DMB> list = dmbDao.getDMBByDmms(depton);
        if(list.size()>0){
            return list.get(0);
        }
        return null;
    }

    public DMB findByBmdm(String yhbm) {
        List<DMB> list = dmbDao.getDMBByDmbh(yhbm);
        if(list.size()>0){
            return list.get(0);
        }
        return null;
    }
}
