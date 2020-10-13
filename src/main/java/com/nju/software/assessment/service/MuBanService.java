package com.nju.software.assessment.service;

import com.nju.software.assessment.bean.MuBan;
import com.nju.software.assessment.dao.MuBanDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MuBanService {

    @Autowired
    MuBanDao muBanDao;


    public int addMuBan(MuBan muBan){ return (muBanDao.save(muBan)).getId();}

    public void alterMuBan(MuBan muBan){  };

    public List<MuBan> getMuBanbyUserAndIsshare(String user,Integer isshare){
        return muBanDao.getMuBanByUserAndIsshare(user,isshare);
    }
    public List<MuBan> getMuBanbyIsshare(Integer isshare){
        return muBanDao.getMuBanByIsshare(isshare);
    }

    public void updateMuBanByIsshare(Integer isshare, Integer id){
        muBanDao.updateMuBanByIsshare(isshare,id);

    }

    public void deleteMuBanById(Integer id){
        muBanDao.deleteMuBanById(id);
    }


}
