package com.nju.software.assessment.dao;

import com.nju.software.assessment.bean.UserAssessDetail;
import com.nju.software.assessment.bean.UserAssessRecord;
import com.nju.software.assessment.bean.UserAuthorityRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserAssessRecordDao extends JpaRepository<UserAssessRecord,Long> {
    @Query(value = "from UserAssessRecord where yhbh in ?1 and objectId in ?2")
    List<UserAssessRecord> findUarByYhbhList(List<Integer> list,List<Long> objectsId);

    @Query(value = "select * from  user_assess_record where yhbh = ?1",nativeQuery = true)
    List<UserAssessRecord> getAssessRecordListByYhbh(String yhbh);

    @Query(value = "from UserAssessRecord where yhbh = ?1 and objectId = ?2")
    List<UserAssessRecord> findAssessRecordByYhbhAndObjectId(Long yhbh, Long objectId);

    @Query(value = "select MAX(user_assess_record_id) from user_assess_record",nativeQuery = true)
    int findMaxUserAssessRecordId();

    @Query(value = "from UserAssessRecord where yhbh = ?1")
    List<UserAssessRecord> findByYhbh(Long yhbh);

    @Transactional
    @Modifying
    @Query(value = "delete from UserAssessRecord where yhbh = ?1")
    void deleteUserAssessRecordByYhbh(Long yhbh);

    @Query(value = "from UserAssessRecord where objectId in ?1")
    List<UserAssessRecord> findUarSecCom(List<Long> objectsId);


    @Query(value = "from UserAssessRecord where yhbh = ?1 and objectId = ?2")
    UserAssessRecord findUarByObjectAndDmbh(Long yhbh, Long i);

    @Query(value = "select userAssessRecordId from UserAssessRecord where yhbh = ?1")
    List<Long> findUarIdByYhbh(Long yhbh);
}
