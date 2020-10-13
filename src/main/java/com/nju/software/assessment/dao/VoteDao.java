package com.nju.software.assessment.dao;

import com.nju.software.assessment.bean.yitou;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoteDao extends JpaRepository<yitou,Integer> {
    List<yitou> findAll();
}
