package com.nju.software.assessment.dao;

import com.nju.software.assessment.bean.AssessUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AssessUserDao extends JpaRepository<AssessUser,String> {

    @Query(value = "select * from  assess_user where username = ?1",nativeQuery = true)
    List<AssessUser> findByMc(String user);
}
