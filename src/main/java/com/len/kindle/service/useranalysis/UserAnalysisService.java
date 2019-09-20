package com.len.kindle.service.useranalysis;

import com.len.kindle.config.Constant;
import com.len.kindle.entity.PayRecord;
import com.len.kindle.entity.PropertyRecord;
import com.len.kindle.entity.UserInfo;
import com.len.kindle.repo.GameRecordRepo;
import com.len.kindle.repo.PayRecordRepo;
import com.len.kindle.repo.PropertyRecordRepo;
import com.len.kindle.repo.UserInfoRepo;
import com.len.kindle.util.FantasticUtil;
import com.len.kindle.vo.GameVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author sujianfeng
 * @date 2018/11/5 12:38 AM
 */
@Slf4j
@Service
public class UserAnalysisService {

    @Autowired
    private GameRecordRepo gameRecordRepo;

    @Autowired
    private UserInfoRepo userInfoRepo;

    @Autowired
    private PayRecordRepo payRecordRepo;

    @Autowired
    private PropertyRecordRepo propertyRecordRepo;


    public List<GameVo> getGameVoList(String[] condition) {
        List<Object[]> objectList = new ArrayList<>();

        if (StringUtils.isBlank(condition[2])) {
            objectList = gameRecordRepo.getGameInfo(condition[0], condition[1]);
        } else if (condition[2].equals(Constant.ALL)) {
            objectList = gameRecordRepo.getGameInfo(condition[0], condition[1]);
        }

        List<GameVo> gameVoList = new ArrayList<>();

        if (objectList.size() > 0) {
            for (Object[] object : objectList) {

                GameVo gameVo = GameVo.builder().d(object[0].toString()).channel(object[1].toString())
                        .gametype(object[2].toString()).peoplecount(Integer.parseInt(object[3].toString()))
                        .gamecount(Integer.parseInt(object[4].toString())).roomcount(Integer.parseInt(object[5].toString()))
                        .build();

                gameVoList.add(gameVo);

            }
        }

        return gameVoList;

    }

    public Page<UserInfo> findAllRmbUser(String[] condition, Pageable pageable) {

        Page<UserInfo> userInfoPage = userInfoRepo.findAll((root, query, cb) -> {

            List<Predicate> list = new ArrayList<>();

            if (StringUtils.isNotBlank(condition[0])) {
                list.add(cb.ge(root.get("rechargeAmount").as(Integer.class), Integer.parseInt(condition[0])));
            }

            if (StringUtils.isNotBlank(condition[1])) {
                list.add(cb.le(root.get("rechargeAmount").as(Integer.class), Integer.parseInt(condition[1])));
            }

            if (StringUtils.isNotBlank(condition[2])) {
                list.add(cb.equal(root.get("id").as(Integer.class), Integer.parseInt(condition[2])));
            }

            if (StringUtils.isNotBlank(condition[3])) {
                list.add(cb.like(root.get("channel").as(String.class), "%" + condition[3] + "%"));
            }
            Predicate[] predicate = new Predicate[list.size()];
            return cb.and(list.toArray(predicate));
        }, pageable);

        Iterator<UserInfo> userInfoIterable = userInfoPage.iterator();
        while (userInfoIterable.hasNext()) {
            UserInfo userInfo = userInfoIterable.next();
            userInfo.setLastdays();
            userInfo.setFengCardInfo();
            userInfo.setLongCardInfo();
            userInfo.setZhiZunCardInfo();
        }
        return userInfoPage;
    }

    public Page<PayRecord> getRechargeInfo(String[] condition, Pageable pageable) {

        Page<PayRecord> payRecordPage = payRecordRepo.findAll((root, query, cb) -> {

            List<Predicate> list = new ArrayList<>();

            //日期条件
            if (StringUtils.isNotBlank(condition[0]) && StringUtils.isNotBlank(condition[1])) {
                try {
                    Date dateBefore = FantasticUtil.SDF_4_NOTIFY.get().parse(condition[0]);
                    Date dateAfter = FantasticUtil.SDF_4_NOTIFY.get().parse(condition[1]);
                    if (dateBefore.after(dateAfter)) {
                        Date tmpDate = dateBefore;
                        dateBefore = dateAfter;
                        dateAfter = tmpDate;
                    }
                    list.add(cb.between(root.get("createTime").as(Date.class), dateBefore, dateAfter));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            if (StringUtils.isNotBlank(condition[2])) {
                list.add(cb.equal(root.get("userID").as(String.class), Integer.parseInt(condition[2])));
            }

            if (StringUtils.isNotBlank(condition[3])) {
                list.add(cb.like(root.get("channel").as(String.class), "%" + condition[3] + "%"));
            }
            list.add(cb.equal(root.get("type").as(Integer.class), 1));
            list.add(cb.equal(root.get("prop").as(Integer.class), 1));


            Predicate[] predicate = new Predicate[list.size()];
            return cb.and(list.toArray(predicate));
        }, pageable);


        Iterator<PayRecord> payRecordIterator = payRecordPage.iterator();
        while (payRecordIterator.hasNext()) {
            PayRecord payRecord = payRecordIterator.next();
            payRecord.setPropName();
            payRecord.setTypeName();
            payRecord.setProducerName();
        }

        return payRecordPage;
    }

    public Page<PayRecord> getOrderIdInfo(String orderId, Pageable pageable) {
        Page<PayRecord> payRecordPage = payRecordRepo.findAll((root, query, cb) -> {

            List<Predicate> list = new ArrayList<>();


            list.add(cb.equal(root.get("orderID").as(String.class), orderId));


            Predicate[] predicate = new Predicate[list.size()];
            return cb.and(list.toArray(predicate));
        }, pageable);

        Iterator<PayRecord> payRecordIterator = payRecordPage.iterator();
        while (payRecordIterator.hasNext()) {
            PayRecord payRecord = payRecordIterator.next();
            payRecord.setBeforeChange();
            payRecord.setPropName();
            payRecord.setTypeName();
            payRecord.setProducerName();
        }

        return payRecordPage;
    }

    public Page<PropertyRecord> getPropertyInfo(String[] condition, Pageable pageable) {
        Page<PropertyRecord> propertyRecordPage = propertyRecordRepo.findAll((root, query, cb) -> {

            List<Predicate> list = new ArrayList<>();

            //日期条件
            if (StringUtils.isNotBlank(condition[0]) && StringUtils.isNotBlank(condition[1])) {
                try {
                    Date dateBefore = FantasticUtil.SDF_4_NOTIFY.get().parse(condition[0]);
                    Date dateAfter = FantasticUtil.SDF_4_NOTIFY.get().parse(condition[1]);
                    if (dateBefore.after(dateAfter)) {
                        Date tmpDate = dateBefore;
                        dateBefore = dateAfter;
                        dateAfter = tmpDate;
                    }
                    list.add(cb.between(root.get("createTime").as(Date.class), dateBefore, dateAfter));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            if (StringUtils.isNotBlank(condition[2])) {
                list.add(cb.equal(root.get("userID").as(String.class), Integer.parseInt(condition[2])));
            }

            if (StringUtils.isNotBlank(condition[3])) {
                list.add(cb.like(root.get("channel").as(String.class), "%" + condition[3] + "%"));
            }

            Predicate[] predicate = new Predicate[list.size()];
            return cb.and(list.toArray(predicate));
        }, pageable);


        Iterator<PropertyRecord> propertyRecordIterator = propertyRecordPage.iterator();
        while (propertyRecordIterator.hasNext()) {
            PropertyRecord propertyRecord = propertyRecordIterator.next();
            propertyRecord.setBeforeChange();
            propertyRecord.setPropName();
            propertyRecord.setTypeName();
            propertyRecord.setProducerName();
        }

        return propertyRecordPage;


    }
}
