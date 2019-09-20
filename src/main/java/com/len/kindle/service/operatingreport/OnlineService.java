package com.len.kindle.service.operatingreport;

import com.len.kindle.repo.LoginRecordRepo;
import com.len.kindle.vo.OnlineVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.len.kindle.config.SystemData.GAMETYPE_MAP;

/**
 * @author Administrator
 * @date 2018/12/4 17:24
 */
@Slf4j
@Service
public class OnlineService {
    @Autowired
    private LoginRecordRepo loginRecordRepo;


    public List<OnlineVo> getOnlieVoList(String starttime, String endtime) {
        List<Object[]> list = loginRecordRepo.getOnlineData(starttime, endtime);

        List<OnlineVo> onlineVoList = new ArrayList<>();
        for (Object[] object : list) {
            OnlineVo onlineVo = OnlineVo.builder().gameType(Integer.parseInt(object[0].toString()))
                    .gameTypeName(GAMETYPE_MAP.get(Integer.parseInt(object[0].toString())))
                    .count1(Integer.parseInt(object[1].toString()))
                    .count2(Integer.parseInt(object[2].toString()))
                    .count3(Integer.parseInt(object[3].toString()))
                    .sumCount(Integer.parseInt(object[4].toString())).build();

            onlineVoList.add(onlineVo);
        }
        return onlineVoList;
    }
}
