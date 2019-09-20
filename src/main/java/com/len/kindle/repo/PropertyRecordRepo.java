package com.len.kindle.repo;

import com.len.kindle.entity.PropertyRecord;
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
public interface PropertyRecordRepo extends JpaRepository<PropertyRecord, Integer>, JpaSpecificationExecutor<PropertyRecord> {

    String GET_LONG_FENG_ZHIZHUN_CARD = "SELECT SUBSTR(createTime,1,10) d,channel,count(DISTINCT (CASE WHEN producer=116 THEN userID ELSE NULL END)) AS fengCardUser,count(CASE WHEN producer=116 THEN userID ELSE NULL END) AS fengCardCount,0-SUM(CASE WHEN producer=116 THEN amount ELSE 0 END) AS fengCardJine,count(DISTINCT (CASE WHEN producer=117 THEN userID ELSE NULL END)) AS longCardUser,count(CASE WHEN producer=117 THEN userID ELSE NULL END) AS longCardCount,0-SUM(CASE WHEN producer=117 THEN amount ELSE 0 END) AS longCardJine,count(DISTINCT (CASE WHEN producer=118 THEN userID ELSE NULL END)) AS zhiZunCardUser,count(CASE WHEN producer=118 THEN userID ELSE NULL END) AS zhiZunCardCount,0-SUM(CASE WHEN producer=118 THEN amount ELSE 0 END) AS zhiZunCardJine FROM property_record WHERE producer IN (116,117,118) AND type=1 AND prop=1 AND createTime BETWEEN :starttime AND :endtime group BY d,channel";

    @Query(value = GET_LONG_FENG_ZHIZHUN_CARD, nativeQuery = true)
    List<Object[]> getLongFengZhizhunCard(@Param("starttime") String starttime, @Param("endtime") String endtime);


    // String GET_VIP_INFO = "SELECT SUBSTR(createTime,1,10) d,channel,count(CASE WHEN vip=1 THEN userID ELSE NULL END) AS vip1User,0-SUM(CASE WHEN vip=1 THEN amount ELSE 0 END) AS vip1Jine,count(CASE WHEN vip=2 THEN userID ELSE NULL END) AS vip2User,0-SUM(CASE WHEN vip=2 THEN amount ELSE 0 END) AS vip2Jine,count(CASE WHEN vip=3 THEN userID ELSE NULL END) AS vip3User,0-SUM(CASE WHEN vip=3 THEN amount ELSE 0 END) AS vip3Jine,count(CASE WHEN vip=4 THEN userID ELSE NULL END) AS vip4User,0-SUM(CASE WHEN vip=4 THEN amount ELSE 0 END) AS vip4Jine,count(CASE WHEN vip=5 THEN userID ELSE NULL END) AS vip5User,0-SUM(CASE WHEN vip=5 THEN amount ELSE 0 END) AS vip5Jine,count(CASE WHEN vip=6 THEN userID ELSE NULL END) AS vip6User,0-SUM(CASE WHEN vip=6 THEN amount ELSE 0 END) AS vip6Jine,count(CASE WHEN vip=7 THEN userID ELSE NULL END) AS vip7User,0-SUM(CASE WHEN vip=7 THEN amount ELSE 0 END) AS vip7Jine,count(CASE WHEN vip=8 THEN userID ELSE NULL END) AS vip8User,0-SUM(CASE WHEN vip=8 THEN amount ELSE 0 END) AS vip8Jine,count(CASE WHEN vip=9 THEN userID ELSE NULL END) AS vip9User,0-SUM(CASE WHEN vip=9 THEN amount ELSE 0 END) AS vip9Jine FROM property_record WHERE prop=1 and vip!=0 AND createTime BETWEEN :starttime AND :endtime GROUP BY d,channel";
    String GET_VIP_INFO = "SELECT SUBSTR(createTime,1,10) d,channel,count(CASE WHEN vip=1 THEN userID ELSE NULL END) AS vip1User,SUM(CASE WHEN vip=1 THEN amount ELSE 0 END) AS \n" +
            "vip1Jine,count(CASE WHEN vip=2 THEN userID ELSE NULL END) AS vip2User,SUM(CASE WHEN vip=2 THEN amount ELSE 0 END) AS vip2Jine,count(CASE WHEN vip=3 THEN userID ELSE \n" +
            "NULL END) AS vip3User,SUM(CASE WHEN vip=3 THEN amount ELSE 0 END) AS vip3Jine,count(CASE WHEN vip=4 THEN userID ELSE NULL END) AS vip4User,SUM(CASE WHEN vip=4 THEN \n" +
            "amount ELSE 0 END) AS vip4Jine,count(CASE WHEN vip=5 THEN userID ELSE NULL END) AS vip5User,SUM(CASE WHEN vip=5 THEN amount ELSE 0 END) AS vip5Jine,count(CASE WHEN \n" +
            "vip=6 THEN userID ELSE NULL END) AS vip6User,SUM(CASE WHEN vip=6 THEN amount ELSE 0 END) AS vip6Jine,count(CASE WHEN vip=7 THEN userID ELSE NULL END) AS \n" +
            "vip7User,SUM(CASE WHEN vip=7 THEN amount ELSE 0 END) AS vip7Jine,count(CASE WHEN vip=8 THEN userID ELSE NULL END) AS vip8User,SUM(CASE WHEN vip=8 THEN amount ELSE 0 \n" +
            "END) AS vip8Jine,count(CASE WHEN vip=9 THEN userID ELSE NULL END) AS vip9User,SUM(CASE WHEN vip=9 THEN amount ELSE 0 END) AS vip9Jine FROM pay_record WHERE \n" +
            "prop=1 and vip!=0 AND createTime BETWEEN :starttime AND :endtime GROUP BY d,channel";

    @Query(value = GET_VIP_INFO, nativeQuery = true)
    List<Object[]> getVipInfo(@Param("starttime") String starttime, @Param("endtime") String endtime);

    String GET_CHANCHU_INFO = "SELECT SUBSTR(createTime,1,10) d,channel,SUM(CASE WHEN producer=103 THEN amount ELSE 0 END) AS mrdl,SUM(CASE WHEN producer=102 THEN amount ELSE 0 END) AS dtbcs,SUM(CASE WHEN producer=408 || producer=708 || producer=1108 THEN amount ELSE 0 END) AS fjbcs,SUM(CASE WHEN producer=106 || producer=412 || producer=712 || producer=1112 THEN amount ELSE 0 END) AS pcbz,SUM(CASE WHEN producer=403 || producer=703 || producer=1103 THEN amount ELSE 0 END) AS xthb,SUM(CASE WHEN producer=105 THEN amount ELSE 0 END) AS vip,SUM(CASE WHEN producer=115 THEN amount ELSE 0 END) AS th,SUM(CASE WHEN producer=114 THEN amount ELSE 0 END) AS sc FROM property_record WHERE prop=3 AND createTime BETWEEN :starttime AND :endtime GROUP BY d,channel";

    @Query(value = GET_CHANCHU_INFO, nativeQuery = true)
    List<Object[]> getChanchuInfo(@Param("starttime") String starttime, @Param("endtime") String endtime);

    String GET_CHARGE_INFO = "SELECT SUBSTR(createTime,1,10) d,channel,COUNT(CASE WHEN (producer=114 AND prop=1) THEN 1 ELSE NULL END) AS sccount,0-SUM(CASE WHEN (producer=114 AND prop=1) THEN amount ELSE 0 END) AS scmoney,SUM(CASE WHEN (producer=114 AND prop=3) THEN amount ELSE 0 END) AS scgold,SUM(CASE WHEN (producer=114 AND prop=8) THEN amount ELSE 0 END) AS scdz,SUM(CASE WHEN (producer=114 AND prop=5) THEN amount ELSE 0 END) AS scts,count(CASE WHEN (producer=102 AND prop=2) THEN 1 ELSE NULL END) AS cqcount,sum(CASE WHEN (producer=102 AND prop=3) THEN amount ELSE 0 END) AS cqgold,0-sum(CASE WHEN (producer=102 AND prop=2) THEN amount ELSE 0 END) AS cqgem,count(CASE WHEN (producer=115 AND prop=1 AND type=1) THEN 1 ELSE NULL END) AS mrthcount,count(CASE WHEN (producer=115 AND prop=3 AND type=1) THEN 1 ELSE NULL END) AS mrthgold,count(CASE WHEN (producer=115 AND prop=5 AND type=3) THEN 1 ELSE NULL END) AS mrthtscard FROM property_record WHERE producer IN (114,102,115) AND createTime BETWEEN :starttime AND :endtime GROUP BY d,channel";

    @Query(value = GET_CHARGE_INFO, nativeQuery = true)
    List<Object[]> getChargeInfo(@Param("starttime") String starttime, @Param("endtime") String endtime);


    String GET_PROJECTFEE_INFO = "SELECT SUBSTR(createTime,1,10) d,channel,SUM(CASE WHEN TYPE=5 THEN amount ELSE 0 END) AS tax, 0-SUM(CASE WHEN TYPE=10 THEN amount ELSE 0 END) AS taifei FROM `property_record` WHERE createTime BETWEEN :starttime AND :endtime AND TYPE IN (5,10) AND amount !=0 GROUP BY d,channel";

    @Query(value = GET_PROJECTFEE_INFO, nativeQuery = true)
    List<Object[]> getProjectFeeInfo(@Param("starttime") String starttime, @Param("endtime") String endtime);


    String GET_CUR_PROPERTY_EXPEND = "SELECT SUBSTR(createTime,1,10) d,channel, 0-SUM(CASE WHEN (prop=2 AND amount < 0)  THEN amount ELSE 0 END) AS gemCount,0-SUM(CASE WHEN (prop=3 AND amount < 0)  THEN amount ELSE 0 END) AS goldCount,SUM(CASE WHEN (prop=4 AND amount > 0)  THEN amount ELSE 0 END) AS etCount,0-SUM(CASE WHEN (prop=5 AND amount < 0)  THEN amount ELSE 0 END) AS tsCount,0-SUM(CASE WHEN (prop=6 AND amount < 0)  THEN amount ELSE 0 END) AS zdCount FROM `property_record` WHERE createTime BETWEEN STR_TO_DATE(DATE_FORMAT(NOW(),'%Y-%m-%d 00:00:00'), '%Y-%m-%d %T') AND STR_TO_DATE(DATE_FORMAT(NOW(),'%Y-%m-%d 23:59:59'), '%Y-%m-%d %T') GROUP BY d,channel HAVING (gemCount>0 || goldCount >0 || etCount >0 || tsCount >0 || zdCount >0 )";

    @Query(value = GET_CUR_PROPERTY_EXPEND, nativeQuery = true)
    List<Object[]> getCurPropertyExpend();

    String GET_EXPEND_INFO = "select SUBSTR(createTime,1,10) d,channel,\n" +
            "sum(CASE WHEN (amount > 0 and prop = 2) THEN amount ELSE 0 END) as gemcount,\n" +
            "0-sum(CASE WHEN (amount < 0 and prop = 2) THEN amount ELSE 0 END) as gemcount1,\n" +
            "sum(CASE WHEN (amount > 0 and prop = 3) THEN amount ELSE 0 END) as goldcount, \n" +
            "0-sum(CASE WHEN (amount < 0 and prop = 3) THEN amount ELSE 0 END) as goldcount1,\n" +
            "sum(CASE WHEN (amount > 0 and prop = 4) THEN amount ELSE 0 END) as ttcount,\n" +
            "0-sum(CASE WHEN (amount < 0 and prop = 4) THEN amount ELSE 0 END) as ttcount1,\n" +
            "SUM(CASE WHEN (amount > 0  and prop = 5) THEN amount ELSE 0 END) AS tscard,\n" +
            "0-SUM(CASE WHEN (amount < 0  and prop = 5) THEN amount ELSE 0 END) AS tscard1,\n" +
            "SUM(CASE WHEN (amount > 0  and prop = 8) THEN amount ELSE 0 END) AS ddcard,\n" +
            "0-SUM(CASE WHEN (amount < 0  and prop = 8) THEN amount ELSE 0 END) AS ddcard1\n" +
            "from property_record where createTime BETWEEN :starttime AND :endtime GROUP BY d,channel";

    @Query(value = GET_EXPEND_INFO, nativeQuery = true)
    List<Object[]> getExpendInfo(@Param("starttime") String starttime, @Param("endtime") String endtime);


    String GET_PUMPING_DATA = "SELECT SUBSTR(createTime,1,10) d,channel," +
            "0-SUM(CASE WHEN type=5 AND (producer=205 || producer=206 || producer=207) THEN amount ELSE 0 END) AS '推筒子扣税'," +
            "0-SUM(CASE WHEN type=5 AND producer=405 THEN amount ELSE 0 END) AS '二人初级场扣税'," +
            "0-SUM(CASE WHEN type=5 AND producer=505 THEN amount ELSE 0 END) AS '二人中级场扣税'," +
            "0-SUM(CASE WHEN type=5 AND producer=605 THEN amount ELSE 0 END) AS '二人高级场扣税'," +
            "0-SUM(CASE WHEN type=5 AND producer=707 THEN amount ELSE 0 END) AS '血战到底初级场扣税'," +
            "0-SUM(CASE WHEN type=5 AND producer=807 THEN amount ELSE 0 END) AS '血战到底中级场扣税'," +
            "0-SUM(CASE WHEN type=5 AND producer=907 THEN amount ELSE 0 END) AS '血战到底高级场扣税'," +
            "0-SUM(CASE WHEN type=5 AND producer=1007 THEN amount ELSE 0 END) AS '血流成河初级场扣税'," +
            "0-SUM(CASE WHEN type=5 AND producer=1107 THEN amount ELSE 0 END) AS '血流成河中级场扣税'," +
            "0-SUM(CASE WHEN type=5 AND producer=1207 THEN amount ELSE 0 END) AS '血流成河高级场扣税'," +
            "0-SUM(CASE WHEN type=10 AND (producer=205 || producer=206 || producer=207) THEN amount ELSE 0 END) AS '推筒子台费'," +
            "0-SUM(CASE WHEN type=10 AND producer=405 THEN amount ELSE 0 END) AS '二人初级场台费'," +
            "0-SUM(CASE WHEN type=10 AND producer=505 THEN amount ELSE 0 END) AS '二人中级场台费'," +
            "0-SUM(CASE WHEN type=10 AND producer=605 THEN amount ELSE 0 END) AS '二人高级场台费'," +
            "0-SUM(CASE WHEN type=10 AND producer=705 THEN amount ELSE 0 END) AS '血战到底初级场台费'," +
            "0-SUM(CASE WHEN type=10 AND producer=805 THEN amount ELSE 0 END) AS '血战到底中级场台费'," +
            "0-SUM(CASE WHEN type=10 AND producer=905 THEN amount ELSE 0 END) AS '血战到底高级场台费'," +
            "0-SUM(CASE WHEN type=10 AND producer=1005 THEN amount ELSE 0 END) AS '血流成河初级场台费'," +
            "0-SUM(CASE WHEN type=10 AND producer=1105 THEN amount ELSE 0 END) AS '血流成河中级场台费'," +
            "0-SUM(CASE WHEN type=10 AND producer=1205 THEN amount ELSE 0 END) AS '血流成河高级场台费' " +
            "FROM property_record WHERE createTime BETWEEN :starttime AND :endtime GROUP BY d,channel ORDER BY d DESC";

    @Query(value = GET_PUMPING_DATA, nativeQuery = true)
    List<Object[]> getPumpingData(@Param("starttime") String starttime, @Param("endtime") String endtime);
}
