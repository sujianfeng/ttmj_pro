package com.len.kindle.service.operatingreport;

import com.len.kindle.repo.LoginRecordRepo;
import com.len.kindle.repo.PayRecordRepo;
import com.len.kindle.repo.PropertyRecordRepo;
import com.len.kindle.repo.UserInfoRepo;
import com.len.kindle.vo.CommonVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author sujianfeng
 * @date 2018/11/4 11:57 PM
 */
@Slf4j
@Service
public class ProjectService {
    @Autowired
    private UserInfoRepo userInfoRepo;
    @Autowired
    private LoginRecordRepo loginRecordRepo;
    @Autowired
    private PayRecordRepo payRecordRepo;
    @Autowired
    private PropertyRecordRepo propertyRecordRepo;

    public List<CommonVo> getProjectVoList(String[] condition) {
        Map<String, CommonVo> userMap = getUserMap(condition);
        Map<String, CommonVo> loginMap = getLoginMap(condition);
        Map<String, CommonVo> payMap = getPayMap(condition);
        Map<String, CommonVo> gemMap = getGemMap(condition);
        Map<String, CommonVo> taxMap = getTaxMap(condition);
        return mergeChargePayList(userMap, loginMap, payMap, gemMap, taxMap);
    }

    private Map<String, CommonVo> getTaxMap(String[] condition) {
        Map<String, CommonVo> map = new HashMap<>();
        List<Object[]> list = propertyRecordRepo.getProjectFeeInfo(condition[0], condition[1]);
        if (list.size() > 0) {
            for (Object[] object : list) {
                CommonVo chargePayVo = CommonVo.builder().dataDate(object[0].toString())
                        .channel(object[1].toString())
                        .tax(Long.parseLong(object[2].toString()))
                        .taifei(Long.parseLong(object[3].toString())).build();
                map.put(object[0].toString() + object[1].toString(), chargePayVo);
            }
        }
        return map;
    }

    private Map<String, CommonVo> getGemMap(String[] condition) {
        Map<String, CommonVo> map = new HashMap<>();
        List<Object[]> list = payRecordRepo.getChargeInfo(condition[0], condition[1]);
        if (list.size() > 0) {
            for (Object[] object : list) {
                CommonVo chargePayVo = CommonVo.builder().dataDate(object[0].toString())
                        .channel(object[1].toString())
                        .gemCount(Integer.parseInt(object[2].toString())).build();
                map.put(object[0].toString() + object[1].toString(), chargePayVo);

            }
        }
        return map;
    }

    private Map<String, CommonVo> getPayMap(String[] condition) {
        Map<String, CommonVo> map = new HashMap<>();
        List<Object[]> list = payRecordRepo.getChargeInfo(condition[0], condition[1]);
        if (list.size() > 0) {
            for (Object[] object : list) {
                CommonVo chargePayVo = CommonVo.builder().dataDate(object[0].toString())
                        .channel(object[1].toString())
                        .totalFee(Integer.parseInt(object[2].toString()))
                        .feeUser(Integer.parseInt(object[3].toString()))
                        .feeCount(Integer.parseInt(object[4].toString())).build();
                map.put(object[0].toString() + object[1].toString(), chargePayVo);

            }
        }
        return map;
    }

    private Map<String, CommonVo> getLoginMap(String[] condition) {
        Map<String, CommonVo> map = new HashMap<>();
        List<Object[]> list = loginRecordRepo.getChargeInfo(condition[0], condition[1]);
        if (list.size() > 0) {
            for (Object[] object : list) {
                CommonVo chargePayVo = CommonVo.builder().dataDate(object[0].toString())
                        .channel(object[1].toString()).regCount(0)
                        .activeCount(Integer.parseInt(object[2].toString())).build();
                map.put(object[0].toString() + object[1].toString(), chargePayVo);

            }
        }
        return map;
    }

    private Map<String, CommonVo> getUserMap(String[] condition) {

        Map<String, CommonVo> map = new HashMap<>();
        List<Object[]> list = userInfoRepo.getChargeInfo(condition[0], condition[1]);
        if (list.size() > 0) {
            for (Object[] object : list) {
                CommonVo chargePayVo = CommonVo.builder().dataDate(object[0].toString())
                        .channel(object[1].toString()).regCount(Integer.parseInt(object[2].toString()))
                        .build();
                map.put(object[0].toString() + object[1].toString(), chargePayVo);
            }
        }
        return map;
    }

    public List<CommonVo> mergeChargePayList(Map<String, CommonVo>... maps) {
        Map<String, CommonVo> mergeMap = new HashMap<>();
        for (Map<String, CommonVo> map : maps) {
            for (String key : map.keySet()) {
                if (mergeMap.containsKey(key)) {
                    CommonVo mergeVo = mergeMap.get(key);
                    CommonVo mapVo = map.get(key);
                    mergeVo.setRegCount(mapVo.getRegCount() > 0 ? mapVo.getRegCount() : mergeVo.getRegCount());
                    mergeVo.setActiveCount(mapVo.getActiveCount() > 0 ? mapVo.getActiveCount() : mergeVo.getActiveCount());
                    mergeVo.setTotalFee(mapVo.getTotalFee() > 0 ? mapVo.getTotalFee() : mergeVo.getTotalFee());
                    mergeVo.setFeeUser(mapVo.getFeeUser() > 0 ? mapVo.getFeeUser() : mergeVo.getFeeUser());
                    mergeVo.setFeeCount(mapVo.getFeeCount() > 0 ? mapVo.getFeeCount() : mergeVo.getFeeCount());
                    mergeVo.setGemCount(mapVo.getGemCount() > 0 ? mapVo.getGemCount() : mergeVo.getGemCount());
                    mergeVo.setTax(mapVo.getTax() > 0 ? mapVo.getTax() : mergeVo.getTax());
                    mergeVo.setTaifei(mapVo.getTaifei() > 0 ? mapVo.getTaifei() : mergeVo.getTaifei());
                } else {
                    mergeMap.put(key, map.get(key));
                }
            }
        }
        List<CommonVo> list = new ArrayList<>(mergeMap.values());
        Collections.sort(list);
        return list;
    }
}
