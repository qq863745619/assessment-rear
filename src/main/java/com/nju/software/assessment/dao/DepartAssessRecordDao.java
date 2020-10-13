package com.nju.software.assessment.dao;

import com.nju.software.assessment.bean.DepartmentAssessRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartAssessRecordDao extends JpaRepository<DepartmentAssessRecord,Long> {

    @Query(value = "select max(departmentAssessRecordId) from DepartmentAssessRecord ")
    int  findMaxId();

    @Query(value = "from DepartmentAssessRecord where dmbh = ?1 and objectId = ?2")
    DepartmentAssessRecord findByObjectAndDmbh(String dmbh, Long objectId);

    @Query(value = "from DepartmentAssessRecord where dmbh = ?1")
    List<Long> findUadIdBydmbh(Long dmbh);
}
