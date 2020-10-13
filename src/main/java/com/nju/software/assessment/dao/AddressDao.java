package com.nju.software.assessment.dao;

import com.nju.software.assessment.bean.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AddressDao extends JpaRepository<Address,String> {
    @Query(value = "from Address where addressId = ?1")
    Address findAddress(String roomNum);
}
