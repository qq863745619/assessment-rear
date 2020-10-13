package com.nju.software.assessment.dao;

import com.nju.software.assessment.bean.Object;
import com.nju.software.assessment.bean.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ObjectDao extends JpaRepository<Object,Long> {
    @Query(value = "select MAX(object_id) from object",nativeQuery = true)
    int findMaxId();


    //找到所有电脑的objectId
    @Query(value = "select objectId from Object where objectType = 1")
    List<Long> findAllComputer();

    @Query(value = "select objectId from Object where objectType = 2")
    List<Long> findAllAutoDevice();
}
