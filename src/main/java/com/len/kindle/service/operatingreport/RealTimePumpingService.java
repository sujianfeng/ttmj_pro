package com.len.kindle.service.operatingreport;

import com.len.kindle.repo.PropertyRecordRepo;
import com.len.kindle.vo.PumpingVo;
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
public class RealTimePumpingService {

    @Autowired
    private PropertyRecordRepo propertyRecordRepo;

    public List<PumpingVo> getPumpingVoList(String[] condition) {
        List<Object[]> list = propertyRecordRepo.getPumpingData(condition[0], condition[1]);

        List<PumpingVo> pumpingVoList = new ArrayList<>();

        for (Object[] object : list) {
            PumpingVo pumpingVo = PumpingVo.builder().d(object[0].toString()).channel(object[1].toString())
                    .tax1(Integer.parseInt(object[2].toString()))
                    .tax2(Integer.parseInt(object[3].toString()))
                    .tax3(Integer.parseInt(object[4].toString()))
                    .tax4(Integer.parseInt(object[5].toString()))
                    .tax5(Integer.parseInt(object[6].toString()))
                    .tax6(Integer.parseInt(object[7].toString()))
                    .tax7(Integer.parseInt(object[8].toString()))
                    .tax8(Integer.parseInt(object[9].toString()))
                    .tax9(Integer.parseInt(object[10].toString()))
                    .tax10(Integer.parseInt(object[11].toString()))

                    .fee1(Integer.parseInt(object[12].toString()))
                    .fee2(Integer.parseInt(object[13].toString()))
                    .fee3(Integer.parseInt(object[14].toString()))
                    .fee4(Integer.parseInt(object[15].toString()))
                    .fee5(Integer.parseInt(object[16].toString()))
                    .fee6(Integer.parseInt(object[17].toString()))
                    .fee7(Integer.parseInt(object[18].toString()))
                    .fee8(Integer.parseInt(object[19].toString()))
                    .fee9(Integer.parseInt(object[20].toString()))
                    .fee10(Integer.parseInt(object[21].toString()))

                    .build();

            pumpingVoList.add(pumpingVo);
        }

        return pumpingVoList;

    }
}
