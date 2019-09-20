package com.len.kindle.service.tableinfo;

import com.len.kindle.entity.HeaderInfo;
import com.len.kindle.repo.HeaderInfoRepo;
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
public class HeaderInfoService {
    @Autowired
    private HeaderInfoRepo headerInfoRepo;

    public Page<HeaderInfo> findAll(String[] condition, Pageable pageable) {
        return headerInfoRepo.findAll((root, query, cb) -> {

            List<Predicate> list = new ArrayList<>();

            if (StringUtils.isNotBlank(condition[0])) {
                if (!("-1".equals(condition[0]))) {
                    list.add(cb.equal(root.get("status").as(Integer.class), condition[0]));
                }
            }

            Predicate[] predicate = new Predicate[list.size()];
            return cb.and(list.toArray(predicate));
        }, pageable);
    }
}
