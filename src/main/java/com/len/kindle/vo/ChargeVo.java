package com.len.kindle.vo;

import lombok.*;

/**
 * @author sujianfeng
 * @date 2018/11/18 8:02 PM
 *
 * 系统充值礼包
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ChargeVo {
    private String d;
    private String channel;

    private int sccount;
    private int scmoney;
    private int scgold;
    private int scdz;
    private int scts;

    private int cqcount;
    private int cqgold;
    private int cqgem;


    private int mrthcount;
    private int mrthgold;
    private int mrthtscard;
}
