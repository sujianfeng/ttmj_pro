package com.len.kindle.repo;

import com.len.kindle.entity.PayRecord;
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
public interface PayRecordRepo extends JpaRepository<PayRecord, Integer>, JpaSpecificationExecutor<PayRecord> {

    String GET_CHARGE_INFO = "SELECT  SUBSTR(createTime,1,10) d,channel,SUM(amount),COUNT(DISTINCT(userID)),COUNT(1) FROM pay_record WHERE createTime BETWEEN :starttime AND :endtime AND `type`=1  AND prop = 1 GROUP BY d,channel";

    @Query(value = GET_CHARGE_INFO, nativeQuery = true)
    List<Object[]> getChargeInfo(@Param("starttime") String starttime, @Param("endtime") String endtime);


    String GET_PROJECTFEE_INFO = "SELECT SUBSTR(createTime,1,10) d,channel,SUM(amount) FROM pay_record WHERE createTime BETWEEN :starttime AND :endtime AND `type`=1  AND prop = 2 GROUP BY d,channel";

    @Query(value = GET_PROJECTFEE_INFO, nativeQuery = true)
    List<Object[]> getProjectFeeInfo(@Param("starttime") String starttime, @Param("endtime") String endtime);


    String GET_EXPEND_INFO = "select SUBSTR(createTime,1,10) d,channel,\n" +
            "sum(CASE WHEN (amount > 0 and prop = 2) THEN amount ELSE 0 END) as gemcount, \n" +
            "sum(CASE WHEN (amount > 0 and prop = 3) THEN amount ELSE 0 END) as goldcount,\n" +
            "sum(CASE WHEN (amount > 0 and prop = 4) THEN amount ELSE 0 END) as ttcount,\n" +
            "sum(CASE WHEN (amount > 0 and prop = 5) THEN amount ELSE 0 END) as tscount,\n" +
            "sum(CASE WHEN (amount > 0 and prop = 8) THEN amount ELSE 0 END) as ddcount \n" +
            "from pay_record where createTime BETWEEN :starttime AND :endtime GROUP BY d,channel";

    @Query(value = GET_EXPEND_INFO, nativeQuery = true)
    List<Object[]> getExpendInfo(@Param("starttime") String starttime, @Param("endtime") String endtime);
}
