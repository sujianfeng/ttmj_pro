package com.len.kindle.service.operatingreport;

import com.len.kindle.entity.PropertyExpend;
import com.len.kindle.repo.PayRecordRepo;
import com.len.kindle.repo.PropertyExpendRepo;
import com.len.kindle.repo.PropertyRecordRepo;
import com.len.kindle.util.FantasticUtil;
import com.len.kindle.vo.ExpendVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Administrator
 * @date 2018/12/4 17:24
 */
@Slf4j
@Service
public class ExpendService {
    @Autowired
    private PropertyExpendRepo propertyExpendRepo;
    @Autowired
    private PropertyRecordRepo propertyRecordRepo;

    @Autowired
    private PayRecordRepo payRecordRepo;

    public Page<PropertyExpend> findAll(String[] condition, Pageable pageable) {

        return propertyExpendRepo.findAll((root, query, cb) -> {

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
                    list.add(cb.between(root.get("d").as(Date.class), dateBefore, dateAfter));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            if (StringUtils.isNotBlank(condition[2])) {
                list.add(cb.like(root.get("channel").as(String.class), "%" + condition[2] + "%"));
            }

            Predicate[] predicate = new Predicate[list.size()];
            return cb.and(list.toArray(predicate));
        }, pageable);
    }

    public List<PropertyExpend> getCurExpendList() throws ParseException {
        List<Object[]> list = propertyRecordRepo.getCurPropertyExpend();

        List<PropertyExpend> propertyExpendList = new ArrayList<>();
        for (Object[] objects : list) {
            PropertyExpend propertyExpend = PropertyExpend.builder().d(FantasticUtil.YYYY_MM_DD.get().parse(objects[0].toString()))
                    .channel(objects[1].toString()).gemCount(Long.parseLong(objects[2].toString()))
                    .goldCount(Long.parseLong(objects[3].toString())).etCount(Long.parseLong(objects[4].toString()))
                    .tsCount(Long.parseLong(objects[5].toString())).zdCount(Long.parseLong(objects[6].toString())).build();
            propertyExpendList.add(propertyExpend);
        }
        return propertyExpendList;
    }

    public List<ExpendVo> getExpendList(String[] condition) {
        List<Object[]> payRecordList = payRecordRepo.getExpendInfo(condition[0], condition[1]);
        List<Object[]> proRecordList = propertyRecordRepo.getExpendInfo(condition[0], condition[1]);

        Map<String, ExpendVo> map = new ConcurrentHashMap<>();
        for (Object[] objects : proRecordList) {
            ExpendVo expendVo = ExpendVo.builder()
                    .key(objects[0].toString() + objects[1].toString())
                    .d(objects[0].toString()).channel(objects[1].toString())
                    .gemInCount(Long.parseLong(objects[2].toString())).gemOutCount(Long.parseLong(objects[3].toString()))
                    .goldInCount(Long.parseLong(objects[4].toString())).goldOutCount(Long.parseLong(objects[5].toString()))
                    .ttInCount(Long.parseLong(objects[6].toString())).ttOutCount(Long.parseLong(objects[7].toString()))
                    .tsInCount(Long.parseLong(objects[8].toString())).tsOutCount(Long.parseLong(objects[9].toString()))
                    .ddInCount(Long.parseLong(objects[10].toString())).ddOutCount(Long.parseLong(objects[11].toString()))
                    .build();
            map.put(expendVo.getKey(), expendVo);
        }


        for (Object[] objects : payRecordList) {
            ExpendVo expendVo = ExpendVo.builder()
                    .key(objects[0].toString() + objects[1].toString())
                    .d(objects[0].toString()).channel(objects[1].toString())
                    .gemInCount(Long.parseLong(objects[2].toString()))
                    .goldInCount(Long.parseLong(objects[3].toString()))
                    .ttInCount(Long.parseLong(objects[4].toString()))
                    .tsInCount(Long.parseLong(objects[5].toString()))
                    .ddInCount(Long.parseLong(objects[6].toString()))
                    .build();
            if (map.containsKey(expendVo.getKey())) {
                ExpendVo expendVoFromMap = map.get(expendVo.getKey());
                expendVoFromMap.setGemInCount(expendVoFromMap.getGemInCount() + expendVo.getGemInCount());
                expendVoFromMap.setGoldInCount(expendVoFromMap.getGoldInCount() + expendVo.getGoldInCount());
                expendVoFromMap.setTtInCount(expendVoFromMap.getTtInCount() + expendVo.getTtInCount());
                expendVoFromMap.setTsInCount(expendVoFromMap.getTsInCount() + expendVo.getTsInCount());
                expendVoFromMap.setDdInCount(expendVoFromMap.getDdInCount() + expendVo.getDdInCount());
            }

        }

        List<ExpendVo> expendVoList = new ArrayList<>(map.values());
        Collections.sort(expendVoList);

        return expendVoList;

    }
}
