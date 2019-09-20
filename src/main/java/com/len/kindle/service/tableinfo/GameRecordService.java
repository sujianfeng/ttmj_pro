package com.len.kindle.service.tableinfo;

import com.len.kindle.entity.GameRecord;
import com.len.kindle.repo.GameRecordRepo;
import com.len.kindle.util.FantasticUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author sujianfeng
 */
@Service
public class GameRecordService {
    @Autowired
    private GameRecordRepo gameRecordRepo;

    public Page<GameRecord> findAll(String[] condition, Pageable pageable) {
        return gameRecordRepo.findAll((root, query, cb) -> {

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
                list.add(cb.equal(root.get("userID").as(Integer.class), condition[2]));
            }

            if (StringUtils.isNotBlank(condition[3])) {
                if (!("0".equals(condition[3]))) {
                    list.add(cb.equal(root.get("gameType").as(Integer.class), condition[3]));
                }
            }
            if (StringUtils.isNotBlank(condition[4])) {
                list.add(cb.equal(root.get("gameID").as(Integer.class), condition[4]));
            }

            if (StringUtils.isNotBlank(condition[5])) {
                if (!("-1".equals(condition[5]))) {
                    list.add(cb.equal(root.get("isStart").as(Integer.class), condition[5]));
                }
            }

            if (StringUtils.isNotBlank(condition[6])) {
                if (!("-1".equals(condition[6]))) {
                    list.add(cb.equal(root.get("roomType").as(Integer.class), condition[6]));
                }
            }
            if (StringUtils.isNotBlank(condition[7])) {
                list.add(cb.equal(root.get("roomID").as(Integer.class), condition[7]));
            }


            Predicate[] predicate = new Predicate[list.size()];
            return cb.and(list.toArray(predicate));
        }, pageable);
    }
}
