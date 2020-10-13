package com.nju.software.assessment.dao;

import com.nju.software.assessment.bean.UserAssessDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserAssessDetailDao extends JpaRepository<UserAssessDetail,Long> {
    @Query(value="from UserAssessDetail where userAssessRecordId = ?1 and isDepartAssess = 0")
    List<UserAssessDetail> getUserAssessDetailByUard(Long uard);

    @Transactional
    @Modifying
    @Query(value = "delete from UserAssessDetail where userAssessRecordId = ?1")
    void deleteUserAssessDetailByUard(Long userAssessRecordId);

    @Query(value = "from UserAssessDetail where userAssessRecordId in ?1 and isSecret = ?2 and isDepartAssess = 0")
    List<UserAssessDetail> findByRecordIdList(List<Long> uarIdList,Integer isSecret);

    @Query(value = "select max(userAssessDetailId) from UserAssessDetail")
    Long findUadMaxId();

    @Query(value = "from UserAssessDetail where secretIsOut=1 or secretIsOut = 2")
    List<UserAssessDetail> findAllUarWillDistribute();

    @Transactional
    @Modifying
    @Query(value = "update UserAssessDetail set secretIsOut = 0 where userAssessDetailId = ?1")
    void updateChangeUadStatus(Long id);



    @Query(value = "from UserAssessDetail where secretIsOut=1")
    List<UserAssessDetail> findAllUarWillDistributeDivide();

    @Transactional
    @Modifying
    @Query(value = "update UserAssessDetail set secretIsOut = 2 where userAssessDetailId = ?1")
    void updateChangeUadStatusDivide(Long id);

    @Query(value = "from UserAssessDetail where userAssessRecordId in ?1 and isDepartAssess = 0")
    List<UserAssessDetail> findUadByUarList(List<Long> userAssessRecordIdList);

    @Query(value = "from UserAssessDetail where userAssessRecordId in ?1 and isDepartAssess = 1")
    List<UserAssessDetail> findUadBydarList(List<Long> userAssessRecordIdList);
}
