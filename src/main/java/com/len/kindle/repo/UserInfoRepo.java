package com.len.kindle.repo;

import com.len.kindle.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author sujianfeng
 */
@Repository
public interface UserInfoRepo extends JpaRepository<UserInfo, Integer>, JpaSpecificationExecutor<UserInfo> {

    String GET_CHARGE_INFO = "SELECT SUBSTR(createTime,1,10) d,channel,COUNT(1) FROM `user_info` WHERE createTime BETWEEN :starttime AND :endtime GROUP BY d,channel";

    @Query(value = GET_CHARGE_INFO, nativeQuery = true)
    List<Object[]> getChargeInfo(@Param("starttime") String starttime, @Param("endtime") String endtime);


    String USER_INVENTORY_SQL = "SELECT DATE_FORMAT(NOW(),'%Y-%c-%d') AS d, channel,SUM(gem),SUM(gold),SUM(et),SUM(ts),SUM(zd) FROM `user_info` GROUP BY channel";

    @Query(value = USER_INVENTORY_SQL, nativeQuery = true)
    List<Object[]> getUserInventory();

}
