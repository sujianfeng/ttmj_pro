package com.len.kindle.vo;

import lombok.*;

/**
 * 实时抽水统计
 *
 * @author sujianfeng
 * @date 2019-06-05 00:14
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class PumpingVo {

    private String d;
    private String channel;

    private int tax1;
    private int tax2;
    private int tax3;
    private int tax4;
    private int tax5;
    private int tax6;
    private int tax7;
    private int tax8;
    private int tax9;
    private int tax10;


    private int fee1;
    private int fee2;
    private int fee3;
    private int fee4;
    private int fee5;
    private int fee6;
    private int fee7;
    private int fee8;
    private int fee9;
    private int fee10;


}
