package com.nju.software.assessment.dao2;

import com.nju.software.assessment.bean2.YHB;
import com.nju.software.assessment.model.LoginInfo;
import com.nju.software.assessment.model.Yh;
import com.nju.software.assessment.model.YhbModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
public interface YhbDao extends JpaRepository<YHB,Integer>{

    //根据部门查找用户
    @Transactional(transactionManager="platformTransactionManagerTwo")
    @Query(value = "select new com.nju.software.assessment.model.YhbModel(y.yhbh,y.yhmc,y.yhsf) from YHB y where yhbm=?1")
    public List<YhbModel> findYhmcByYhbm(String yhbm);
    @Query(value = "select new com.nju.software.assessment.model.Yh(y.yhmc,y.yhkl,y.yhsf,y.yhbh) from YHB y where yhmc=?1")
    public List<Yh> findByYhmc(String yhmc);

    public List<YHB> findYHBByYhmc(String yhmc);
    public List<YHB> findYHBByYhdm(String yhdm);

    @Query(value = "select yhbh from YHB where yhmc =?1")
    public Integer getIdByName(String name);
    public List<YHB> findByYhbh(Integer yhbh);

    @Query(value = "select new com.nju.software.assessment.model.LoginInfo(y.yhbh,y.yhmc,y.yhdm,y.yhkl,y.yhsf) from YHB y where yhdm=?1")
    public List<LoginInfo> findByYhdm(String yhdm);

    @Query(value = "select new com.nju.software.assessment.model.YhbModel(y.yhbh,y.yhmc,y.yhsf) from YHB y")
    List<YhbModel> findAllUser();

    @Query(value = "from YHB where yhmc =?1")
    List<YHB> findByYhbmc(String username);

    @Query(value = "from YHB where yhbm =?1")
    List<YHB> findYhmcByYhbbm(String yhbm);

    @Query(value = "from YHB where yhbm in ?1")
    List<YHB> findByYhbmList(List<String> bmList);
}
