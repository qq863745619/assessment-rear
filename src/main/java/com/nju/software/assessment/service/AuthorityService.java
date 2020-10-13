package com.nju.software.assessment.service;

import com.nju.software.assessment.bean.AssessDistribution;
import com.nju.software.assessment.bean.AssessUser;
import com.nju.software.assessment.bean.Authority;
import com.nju.software.assessment.bean.UserAuthorityRecord;
import com.nju.software.assessment.dao.AssessDistributionDao;
import com.nju.software.assessment.dao.AssessUserDao;
import com.nju.software.assessment.dao.AuthorityDao;
import com.nju.software.assessment.dao.UserAuthorityRecordDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthorityService {
    @Autowired
    private AuthorityDao authorityDao;

    @Autowired
    private UserAuthorityRecordDao userAuthorityRecordDao;

    @Autowired
    private AssessUserDao assessUserDao;

    @Autowired
    private AssessDistributionDao assessDistributionDao;
    public List<Authority> findAllAuthority() {
        return authorityDao.findAll();
    }

    public void saveAllUserAuthorityRecord(List<UserAuthorityRecord> uarlist) {
        userAuthorityRecordDao.saveAll(uarlist);
    }

    public void deleteAllUserAuthorityRecord(Long qxbh,String bmbh,Long yhbh){
        userAuthorityRecordDao.deleteAllAuthority(qxbh,bmbh,yhbh);
    }

    public void saveAuthority(Authority auth) {
        authorityDao.save(auth);
    }

    public boolean hasVoteAuthority(Integer yhbh, int qxbh) {
        System.out.println(userAuthorityRecordDao.selectByYhbhAndQxbh(yhbh,qxbh).size());
        return userAuthorityRecordDao.selectByYhbhAndQxbh(yhbh,qxbh).size()>=1;
    }

    public void saveAllAssessUser(List<AssessUser> list) {
        assessUserDao.saveAll(list);
    }

    public AssessUser getAuByName(String user) {
        List<AssessUser> list = assessUserDao.findByMc(user);
        if(list.size()>0){
            return list.get(0);
        }
        return null;
    }

    public void saveAllAssessDistribution(List<AssessDistribution> adlist) {
        assessDistributionDao.saveAll(adlist);
    }

    public List<AssessUser> findAllAssessUser() {
        return assessUserDao.findAll();
    }

    public List<UserAuthorityRecord> findAllAuthorityRecord() {
        return userAuthorityRecordDao.findAll();
    }

    public Authority findByAuthorityId(Long qxbh) {
        return authorityDao.findById(qxbh).get();
    }



    public List<Long> findAuthorityByYhbh(Long yhbh) {
        return  userAuthorityRecordDao.findAuthorityByYhbh(yhbh);
    }
}
