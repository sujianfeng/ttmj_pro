package com.len.kindle.service.tableinfo;

import com.len.kindle.entity.UserInfo;
import com.len.kindle.repo.UserInfoRepo;
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
public class UserInfoService {
    @Autowired
    private UserInfoRepo userInfoRepo;

    public Page<UserInfo> findAll(String[] condition, Pageable pageable) {
        return userInfoRepo.findAll((root, query, cb) -> {

            List<Predicate> list = new ArrayList<>();

            if (StringUtils.isNotBlank(condition[0])) {
                list.add(cb.like(root.get("account").as(String.class), "%" + condition[0] + "%"));
            }

            if (StringUtils.isNotBlank(condition[1])) {
                list.add(cb.like(root.get("nickname").as(String.class), "%" + condition[1] + "%"));
            }

            Predicate[] predicate = new Predicate[list.size()];
            return cb.and(list.toArray(predicate));
        }, pageable);
    }
}
