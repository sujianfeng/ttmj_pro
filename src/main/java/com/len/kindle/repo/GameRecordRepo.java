package com.len.kindle.repo;

import com.len.kindle.entity.GameRecord;
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
public interface GameRecordRepo extends JpaRepository<GameRecord, Integer>, JpaSpecificationExecutor<GameRecord> {

    String GET_GAME_INFO = "SELECT SUBSTR(createTime,1,10) d,channel,CASE gameType WHEN 0 THEN '二人场' WHEN 3 THEN '推筒子' WHEN 5 THEN '仙人夺宝' WHEN 6 THEN '血流成河' WHEN 7 THEN '豹子王' WHEN 9 THEN '血战到底' WHEN 1000 THEN '大厅' ELSE '未知' END AS gametype,count(DISTINCT (userID)) peoplecount,COUNT(CASE WHEN (peopleMax !=0) THEN 1 ELSE NULL END) AS gamecount,COUNT(CASE WHEN (peopleMax !=0) THEN 1 ELSE NULL END) AS roomcount FROM game_record WHERE createTime BETWEEN :starttime AND :endtime GROUP BY d,channel,gametype";

    @Query(value = GET_GAME_INFO, nativeQuery = true)
    List<Object[]> getGameInfo(@Param("starttime") String starttime, @Param("endtime") String endtime);
}
