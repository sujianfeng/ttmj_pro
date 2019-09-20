package com.len.kindle.service.operatingreport;

import com.len.kindle.repo.OnlineRecordRepo;
import com.len.kindle.vo.RealTimeDataVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sujianfeng
 * @date 2019-03-17 15:07
 */
@Slf4j
@Service
public class RealTimeDataService {

    @Autowired
    private OnlineRecordRepo onlineRecordRepo;

    public List<RealTimeDataVo> getOnlineVoList(int gametype, String starttime, String endtime) {
        List<Object[]> list = onlineRecordRepo.getOnlineData(gametype, starttime, endtime);

        List<RealTimeDataVo> onlineVoList = new ArrayList<>();
        for (Object[] object : list) {
            RealTimeDataVo realTimeDataVo = RealTimeDataVo.builder().createTime(object[0].toString())
                    .count1(Integer.parseInt(object[1].toString()))
                    .count2(Integer.parseInt(object[2].toString()))
                    .count3(Integer.parseInt(object[3].toString()))
                    .build();
            onlineVoList.add(realTimeDataVo);
        }
        return onlineVoList;

    }
}
