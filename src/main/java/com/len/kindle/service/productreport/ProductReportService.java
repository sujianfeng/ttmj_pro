package com.len.kindle.service.productreport;

import com.len.kindle.config.Constant;
import com.len.kindle.repo.PropertyRecordRepo;
import com.len.kindle.vo.CardVo;
import com.len.kindle.vo.ChargeVo;
import com.len.kindle.vo.OutputVo;
import com.len.kindle.vo.VipVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sujianfeng
 * @date 2018/11/4 11:23 PM
 */
@Slf4j
@Service
public class ProductReportService {
    @Autowired
    private PropertyRecordRepo propertyRecordRepo;

    public List<CardVo> getCardVoList(String[] condition) {
        List<Object[]> objectList = new ArrayList<>();

        if (StringUtils.isBlank(condition[2])) {
            objectList = propertyRecordRepo.getLongFengZhizhunCard(condition[0], condition[1]);
        } else if (condition[2].equals(Constant.ALL)) {
            objectList = propertyRecordRepo.getLongFengZhizhunCard(condition[0], condition[1]);
        }

        List<CardVo> cardVoList = new ArrayList<>();

        if (objectList.size() > 0) {
            for (Object[] object : objectList) {
                CardVo cardVo = CardVo.builder().d(object[0].toString()).channel(object[1].toString())
                        .fengUser(Integer.parseInt(object[2].toString())).fengCount(Integer.parseInt(object[3].toString())).fengJine(Integer.parseInt(object[4].toString()))
                        .longUser(Integer.parseInt(object[5].toString())).longCount(Integer.parseInt(object[6].toString())).longJine(Integer.parseInt(object[7].toString()))
                        .zhizhunUser(Integer.parseInt(object[8].toString())).zhizhunCount(Integer.parseInt(object[9].toString())).zhizhunJine(Integer.parseInt(object[10].toString())).build();

                cardVoList.add(cardVo);
            }
        }

        return cardVoList;
    }

    public List<VipVo> getVipVoList(String[] condition) {
        List<Object[]> objectList = new ArrayList<>();

        if (StringUtils.isBlank(condition[2])) {
            objectList = propertyRecordRepo.getVipInfo(condition[0], condition[1]);
        } else if (condition[2].equals(Constant.ALL)) {
            objectList = propertyRecordRepo.getVipInfo(condition[0], condition[1]);
        }

        List<VipVo> vipVoList = new ArrayList<>();

        if (objectList.size() > 0) {
            for (Object[] object : objectList) {
                VipVo vipVo = VipVo.builder().d(object[0].toString()).channel(object[1].toString())
                        .vip1User(Integer.parseInt(object[2].toString())).vip1Jine(Integer.parseInt(object[3].toString()))
                        .vip2User(Integer.parseInt(object[4].toString())).vip2Jine(Integer.parseInt(object[5].toString()))
                        .vip3User(Integer.parseInt(object[6].toString())).vip3Jine(Integer.parseInt(object[7].toString()))
                        .vip4User(Integer.parseInt(object[8].toString())).vip4Jine(Integer.parseInt(object[9].toString()))
                        .vip5User(Integer.parseInt(object[10].toString())).vip5Jine(Integer.parseInt(object[11].toString()))
                        .vip6User(Integer.parseInt(object[12].toString())).vip6Jine(Integer.parseInt(object[13].toString()))
                        .vip7User(Integer.parseInt(object[14].toString())).vip7Jine(Integer.parseInt(object[15].toString()))
                        .vip8User(Integer.parseInt(object[16].toString())).vip8Jine(Integer.parseInt(object[17].toString()))
                        .vip9User(Integer.parseInt(object[18].toString())).vip9Jine(Integer.parseInt(object[19].toString()))
                        .build();
                vipVoList.add(vipVo);
            }
        }

        return vipVoList;
    }

    public List<OutputVo> getChanchuVoList(String[] condition) {
        List<Object[]> objectList = new ArrayList<>();

        if (StringUtils.isBlank(condition[2])) {
            objectList = propertyRecordRepo.getChanchuInfo(condition[0], condition[1]);
        } else if (condition[2].equals(Constant.ALL)) {
            objectList = propertyRecordRepo.getChanchuInfo(condition[0], condition[1]);
        }

        List<OutputVo> chanchuVoList = new ArrayList<>();

        if (objectList.size() > 0) {
            for (Object[] object : objectList) {
                OutputVo chanchuVo = OutputVo.builder().d(object[0].toString()).channel(object[1].toString())
                        .mrdl(Integer.parseInt(object[2].toString())).dtbcs(Integer.parseInt(object[3].toString()))
                        .fjbcs(Integer.parseInt(object[4].toString())).bdzh(0)
                        .pcbz(Integer.parseInt(object[5].toString())).htsw(0)
                        .xthb(Integer.parseInt(object[6].toString())).vip(Integer.parseInt(object[7].toString()))
                        .th(Integer.parseInt(object[8].toString())).sc(Integer.parseInt(object[9].toString()))
                        .build();
                chanchuVoList.add(chanchuVo);

            }
        }

        return chanchuVoList;
    }

    public List<ChargeVo> getChargeVoList(String[] condition) {
        List<Object[]> objectList = new ArrayList<>();

        if (StringUtils.isBlank(condition[2])) {
            objectList = propertyRecordRepo.getChargeInfo(condition[0], condition[1]);
        } else if (condition[2].equals(Constant.ALL)) {
            objectList = propertyRecordRepo.getChargeInfo(condition[0], condition[1]);
        }

        List<ChargeVo> chargeVoList = new ArrayList<>();

        if (objectList.size() > 0) {
            for (Object[] object : objectList) {

                ChargeVo chargeVo = ChargeVo.builder().d(object[0].toString()).channel(object[1].toString())
                        .sccount(Integer.parseInt(object[2].toString())).scmoney(Integer.parseInt(object[3].toString()))
                        .scgold(Integer.parseInt(object[4].toString())).scdz(Integer.parseInt(object[5].toString()))
                        .scts(Integer.parseInt(object[6].toString()))
                        .cqcount(Integer.parseInt(object[7].toString()))
                        .cqgold(Integer.parseInt(object[8].toString())).cqgem(Integer.parseInt(object[9].toString()))
                        .mrthcount(Integer.parseInt(object[10].toString()))
                        .mrthgold(Integer.parseInt(object[11].toString())).mrthtscard(Integer.parseInt(object[12].toString()))
                        .build();

                chargeVoList.add(chargeVo);

            }
        }

        return chargeVoList;
    }
}
