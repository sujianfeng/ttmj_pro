package com.len.kindle.repo;

import com.len.kindle.entity.LoginRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Administrator
 */
@Repository
public interface LoginRecordRepo extends JpaRepository<LoginRecord, Integer>, JpaSpecificationExecutor<LoginRecord> {

    String GET_CHARGE_INFO = "SELECT SUBSTR(createTime,1,10) d,channel,COUNT(DISTINCT(userID)) FROM login_record  WHERE createTime BETWEEN :starttime AND :endtime GROUP BY d,channel;";

    @Query(value = GET_CHARGE_INFO, nativeQuery = true)
    List<Object[]> getChargeInfo(@Param("starttime") String starttime, @Param("endtime") String endtime);


    String GET_ONLINE_DATA = "SELECT" +
            " gameType, " +
            " COUNT( CASE WHEN gameLevel = 1 THEN 1 ELSE NULL END ) AS count1, " +
            " COUNT( CASE WHEN gameLevel = 2 THEN 1 ELSE NULL END ) AS count2, " +
            " COUNT( CASE WHEN gameLevel = 3 THEN 1 ELSE NULL END ) AS count3, " +
            " ( " +
            " COUNT( CASE WHEN gameLevel = 1 THEN 1 ELSE NULL END ) + COUNT( CASE WHEN gameLevel = 2 THEN 1 ELSE NULL END ) + COUNT( CASE WHEN gameLevel = 3 THEN 1 ELSE NULL END )  " +
            " ) AS sumcount  " +
            "FROM " +
            " login_record  " +
            "WHERE " +
            " createTime BETWEEN :starttime  " +
            " AND :endtime  " +
            " AND gameType != 1000  " +
            "GROUP BY " +
            " gameType";

    @Query(value = GET_ONLINE_DATA, nativeQuery = true)
    List<Object[]> getOnlineData(@Param("starttime") String starttime, @Param("endtime") String endtime);
}
