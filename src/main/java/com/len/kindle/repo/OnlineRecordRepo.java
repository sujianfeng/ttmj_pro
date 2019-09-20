package com.len.kindle.repo;

import com.len.kindle.entity.OnlineRecord;
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
public interface OnlineRecordRepo extends JpaRepository<OnlineRecord, Integer>, JpaSpecificationExecutor<OnlineRecord> {

    String GET_ONLINE_DATA = "SELECT " +
            " createTime, " +
            " SUM( CASE WHEN gameLevel = 1 THEN `count` ELSE 0 END ) AS count1, " +
            " SUM( CASE WHEN gameLevel = 2 THEN `count` ELSE 0 END ) AS count2, " +
            " SUM( CASE WHEN gameLevel = 3 THEN `count` ELSE 0 END ) AS count3  " +
            "FROM " +
            " online_record  " +
            "WHERE " +
            " gameType = :gametype  " +
            " AND createTime BETWEEN :starttime  " +
            " AND :endtime  " +
            "GROUP BY " +
            " createTime";

    @Query(value = GET_ONLINE_DATA, nativeQuery = true)
    List<Object[]> getOnlineData(@Param("gametype") int gametype, @Param("starttime") String starttime, @Param("endtime") String endtime);
}
