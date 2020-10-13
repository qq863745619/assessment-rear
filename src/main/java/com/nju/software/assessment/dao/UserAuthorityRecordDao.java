package com.nju.software.assessment.dao;

import com.nju.software.assessment.bean.UserAuthorityRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserAuthorityRecordDao extends JpaRepository<UserAuthorityRecord,Long> {
    @Query(value = "delete from  user_authority_record where qxbh = ?1 and bmbh = ?2 and yhbh = ?3",nativeQuery = true)
    @Modifying
    @Transactional
    void deleteAllAuthority(Long qxbh,String bmbh,Long yhbh);

    @Query(value = "select * from  user_authority_record where yhbh = ?1 and qxbh = ?2",nativeQuery = true)
    List<UserAuthorityRecord>  selectByYhbhAndQxbh(Integer yhbh, int qxbh);


    @Query(value = "select qxbh from UserAuthorityRecord where yhbh = ?1")
    List<Long> findAuthorityByYhbh(Long yhbh);
}
