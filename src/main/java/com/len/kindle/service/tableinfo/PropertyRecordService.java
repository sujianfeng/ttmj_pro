package com.len.kindle.service.tableinfo;

import com.len.kindle.entity.PropertyRecord;
import com.len.kindle.repo.PropertyRecordRepo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sujianfeng
 */
@Service
public class PropertyRecordService {
    @Autowired
    private PropertyRecordRepo propertyRecordRepo;

    public Page<PropertyRecord> findAll(String[] condition, Pageable pageable) {
        return propertyRecordRepo.findAll((root, query, cb) -> {

            List<Predicate> list = new ArrayList<>();

            if (StringUtils.isNotBlank(condition[0])) {
                list.add(cb.like(root.get("userID").as(String.class), "%" + condition[0] + "%"));
            }

            Predicate[] predicate = new Predicate[list.size()];
            return cb.and(list.toArray(predicate));
        }, pageable);
    }
}
