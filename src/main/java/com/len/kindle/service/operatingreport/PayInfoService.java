package com.len.kindle.service.operatingreport;

import com.len.kindle.repo.LoginRecordRepo;
import com.len.kindle.repo.PayRecordRepo;
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
public class PayInfoService {
    @Autowired
    private UserInfoRepo userInfoRepo;
    @Autowired
    private LoginRecordRepo loginRecordRepo;
    @Autowired
    private PayRecordRepo payRecordRepo;

    public List<CommonVo> getChargePayVoList(String[] condition) {
        Map<String, CommonVo> userMap = getUserMap(condition);
        Map<String, CommonVo> loginMap = getLoginMap(condition);
        Map<String, CommonVo> payMap = getPayMap(condition);
        return mergeChargePayList(userMap, loginMap, payMap);
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
                        .channel(object[1].toString())
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
                } else {
                    mergeMap.put(key, map.get(key));
                }
            }
        }

        for (CommonVo vo : mergeMap.values()) {
            vo.setActiveArpu(vo.getActiveCount() == 0 ? 0 : (double) vo.getTotalFee() / vo.getActiveCount());
            vo.setFeeArpu(vo.getFeeUser() == 0 ? 0 : (double) vo.getTotalFee() / vo.getFeeUser());
            vo.setActiveFeeRate(vo.getActiveCount() == 0 ? 0 : (double) vo.getFeeUser() / vo.getActiveCount() * 100);
            vo.setRegFeeRate(vo.getRegCount() == 0 ? 0 : (double) vo.getFeeUser() / vo.getRegCount() * 100);
        }
        List<CommonVo> list = new ArrayList<>(mergeMap.values());
        Collections.sort(list);
        return list;
    }
}
