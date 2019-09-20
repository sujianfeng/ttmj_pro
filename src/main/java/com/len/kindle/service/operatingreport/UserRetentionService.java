package com.len.kindle.service.operatingreport;

import com.len.kindle.entity.UserRetention;
import com.len.kindle.repo.UserRetentionRepo;
import com.len.kindle.util.FantasticUtil;
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
import java.util.List;

/**
 * @author sujianfeng
 * @date 2019-01-23 23:54
 */
@Slf4j
@Service
public class UserRetentionService {

    @Autowired
    private UserRetentionRepo userRetentionRepo;

    public Page<UserRetention> findAll(String[] condition, Pageable pageable) {
        return userRetentionRepo.findAll((root, query, cb) -> {

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
}
