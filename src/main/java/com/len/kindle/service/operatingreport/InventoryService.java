package com.len.kindle.service.operatingreport;

import com.len.kindle.entity.GameConfig;
import com.len.kindle.entity.UserInventory;
import com.len.kindle.repo.UserInfoRepo;
import com.len.kindle.repo.UserInventoryRepo;
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
import java.util.Iterator;
import java.util.List;

/**
 * @author Administrator
 * @date 2018/12/4 17:24
 */
@Slf4j
@Service
public class InventoryService {
    @Autowired
    private UserInventoryRepo userInventoryRepo;
    @Autowired
    private UserInfoRepo userInfoRepo;

    public Page<UserInventory> findAll(String[] condition, Pageable pageable, UserInventory sumInventory) {

        Page<UserInventory> userInventoryPage = userInventoryRepo.findAll((root, query, cb) -> {

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


        Iterator<UserInventory> userInventoryIterator = userInventoryPage.iterator();
        while (userInventoryIterator.hasNext()) {
            UserInventory userInventory = userInventoryIterator.next();
            if ((!userInventory.getChannel().equalsIgnoreCase("0"))
                    && (!userInventory.getChannel().equalsIgnoreCase("100000"))) {
                sumInventory.setEtCount(sumInventory.getEtCount() + userInventory.getEtCount());
                sumInventory.setGemCount(sumInventory.getGemCount() + userInventory.getGemCount());
                sumInventory.setGoldCount(sumInventory.getGoldCount() + userInventory.getGoldCount());
                sumInventory.setTsCount(sumInventory.getTsCount() + userInventory.getTsCount());
                sumInventory.setZdCount(sumInventory.getZdCount() + userInventory.getZdCount());
            }
        }

        return userInventoryPage;

    }

    public List<UserInventory> getCurInventoryList() throws ParseException {
        List<Object[]> list = userInfoRepo.getUserInventory();

        List<UserInventory> userInventoryList = new ArrayList<>();
        for (Object[] objects : list) {
            UserInventory userInventory = UserInventory.builder().d(FantasticUtil.YYYY_MM_DD.get().parse(objects[0].toString()))
                    .channel(objects[1].toString()).gemCount(Long.parseLong(objects[2].toString()))
                    .goldCount(Long.parseLong(objects[3].toString())).etCount(Long.parseLong(objects[4].toString()))
                    .tsCount(Long.parseLong(objects[5].toString())).zdCount(Long.parseLong(objects[6].toString())).build();
            userInventoryList.add(userInventory);
        }

        UserInventory sumInventory = UserInventory.builder().channel("合计").etCount(0).goldCount(0)
                .gemCount(0).tsCount(0).zdCount(0).build();

        for (UserInventory userInventory : userInventoryList) {
            if ((!userInventory.getChannel().equalsIgnoreCase("0"))
                    && (!userInventory.getChannel().equalsIgnoreCase("100000"))) {
                sumInventory.setEtCount(sumInventory.getEtCount() + userInventory.getEtCount());
                sumInventory.setGemCount(sumInventory.getGemCount() + userInventory.getGemCount());
                sumInventory.setGoldCount(sumInventory.getGoldCount() + userInventory.getGoldCount());
                sumInventory.setTsCount(sumInventory.getTsCount() + userInventory.getTsCount());
                sumInventory.setZdCount(sumInventory.getZdCount() + userInventory.getZdCount());
            }
        }
        userInventoryList.add(sumInventory);

        return userInventoryList;
    }
}
