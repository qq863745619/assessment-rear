package com.nju.software.assessment.dao;

import com.nju.software.assessment.bean.Ledger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LedgerDao extends JpaRepository<Ledger,Long> {
}
