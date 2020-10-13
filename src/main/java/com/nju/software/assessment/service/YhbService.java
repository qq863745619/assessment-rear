package com.nju.software.assessment.service;

import com.nju.software.assessment.bean2.YHB;
import com.nju.software.assessment.dao2.YhbDao;
import com.nju.software.assessment.model.LoginInfo;
import com.nju.software.assessment.model.Yh;
import com.nju.software.assessment.model.YhbModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YhbService {

    @Autowired
    YhbDao yhbDao;

    public List<YhbModel> findYhmcByYhbm(String yhbm){
        return yhbDao.findYhmcByYhbm(yhbm);
    }

    public Yh findByYhmc(String yhmc){
        List<Yh> yhs= yhbDao.findByYhmc(yhmc);
        if(yhs.size()>0) return yhs.get(0);
        else return null;
    }
    public String findPhoneByYhmc(String yhmc){
        List<YHB> yhb= yhbDao.findYHBByYhmc(yhmc);
        if(yhb.size()>0) return yhb.get(0).getPhone();
        else return null;
    }

    public YHB findYHBByYhmc(String yhmc){
        List<YHB> yhs= yhbDao.findYHBByYhmc(yhmc);
        if(yhs.size()>0) return yhs.get(0);
        else return null;
    }
    public YHB findYHBByYhdm(String yhdm){
        List<YHB> yhs= yhbDao.findYHBByYhdm(yhdm);
        if(yhs.size()>0) return yhs.get(0);
        else return null;
    }
    public YHB findByYhbh(Integer yhbh){
        List<YHB> yhs = yhbDao.findByYhbh(yhbh);
        if(yhs.size()>0) return yhs.get(0);
        else return null;

    }
    public String fingSfByYhmc(String yhmc){
        List<Yh> yhs = yhbDao.findByYhmc(yhmc);
        if(yhs.size()>0) {
            if(yhs.get(0).getYhsf()==null) return "";
            else return yhs.get(0).getYhsf();
        }else return "";
    }

    public LoginInfo findByYhdm(String yhdm){
        List<LoginInfo> logininfo = yhbDao.findByYhdm(yhdm);
        if(logininfo.size()>0) return logininfo.get(0);
        else return null;
    }

    public List<YhbModel> findAll() {
        return yhbDao.findAllUser();
    }

    public void saveAllYh(List<YHB> yhlist) {
        yhbDao.saveAll(yhlist);
    }

    public YHB findByYhbmc(String username) {
        List<YHB> yhs= yhbDao.findByYhbmc(username);
        if(yhs.size()>0) return yhs.get(0);
        else return null;
    }

    public List<YHB> findAllAssessUser() {
        return yhbDao.findAll();
    }

    public List<YHB> findYhmcByYhbbm(String yhbm) {
        return yhbDao.findYhmcByYhbbm(yhbm);
    }

    public List<YHB> findByYhbmList(List<String> bmList) {
        return yhbDao.findByYhbmList(bmList);
    }

    public List<YHB> findAllYHB() {
        return yhbDao.findAll();
    }
}
