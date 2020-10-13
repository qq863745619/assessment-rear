package com.nju.software.assessment.dao;

import com.nju.software.assessment.bean.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityDao extends JpaRepository<Authority,Long> {
}
