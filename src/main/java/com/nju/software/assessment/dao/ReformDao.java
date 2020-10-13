package com.nju.software.assessment.dao;

import com.nju.software.assessment.bean.Reform;
import org.springframework.data.jpa.repository.JpaRepository;
import java.sql.Timestamp;
import java.util.List;

public interface ReformDao extends JpaRepository<Reform,Integer> {
      public List<Reform> findByPublishId(String publishId);
      public List<Reform> findByPublishIdAndUserId(String publishId,Integer userId);
      public List<Reform> findByUserIdAndIsReform(Integer userId,Integer isReform);
      public List<Reform> findByUserIdAndReformTimeBetween(Integer userId, Timestamp start, Timestamp end);
}
