package com.nju.software.assessment.dao;

import com.nju.software.assessment.bean.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductDao extends JpaRepository<Product,Long> {
    @Query(value = "from Product where objectId = ?1")
    List<Product> findByObjectId(Long objectId);

    @Query(value = "select o.object_name from product p inner join object o where p.object_id = o.object_id and p.product_id = ?1",nativeQuery = true)
    String findObjectNameByProductId(Long productId);

    @Query(value = "select productName from Product where productId = ?1")
    String findProductNameByProductId(Long productId);

    @Query(value = "select max(productId) from Product")
    Long findProductMaxId();

    @Query(value = "from Product where productName = ?1")
    List<Product> findByName(String productType);
}
