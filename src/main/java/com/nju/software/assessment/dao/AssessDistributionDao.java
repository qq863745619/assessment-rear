package com.nju.software.assessment.dao;

import com.nju.software.assessment.bean.AssessDistribution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AssessDistributionDao extends JpaRepository<AssessDistribution,String> {

    @Query(value = "from AssessDistribution where yhbh=?1")
    List<AssessDistribution> getAssessDistributionByYhbh(Integer yhbh);
}
