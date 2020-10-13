package com.nju.software.assessment.dao2;

import com.nju.software.assessment.bean2.DMB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
public interface DmbDao extends JpaRepository<DMB,String> {


    List<DMB> getDMBByDmbh(String dmbh);
    List<DMB> getDMBByLbbh(String lbbh);
    List<DMB> getDMBByDmms(String dmms);
}
