package com.nju.software.assessment.dao;

import com.nju.software.assessment.bean.MuBan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MuBanDao extends JpaRepository<MuBan,Integer> {


    List<MuBan> getMuBanByUserAndIsshare(String user,Integer isshare);
    List<MuBan> getMuBanByIsshare(Integer isshare);


    @Query(value = "update muban set isshare=?1 where id=?2",nativeQuery = true)
    @Modifying
    @Transactional(transactionManager="platformTransactionManagerOne")
    void updateMuBanByIsshare(Integer isshare,Integer id);

    @Transactional(transactionManager="platformTransactionManagerOne")
    void deleteMuBanById(Integer id);
}
