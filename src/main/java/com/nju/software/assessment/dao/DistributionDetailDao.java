package com.nju.software.assessment.dao;

import com.nju.software.assessment.bean.DistributionDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DistributionDetailDao extends JpaRepository<DistributionDetail,String> {
    @Query(value = "from DistributionDetail where distributionId = ?1 and objectId = ?2")
    List<DistributionDetail> findByDidAndObjName(String distributionId, Long objectId);


    @Transactional
    @Modifying
    @Query(value = "delete from DistributionDetail where distributionId = ?1")
    void deleteDistributionDetailByAssessDistributionId(String distributionId);
}
