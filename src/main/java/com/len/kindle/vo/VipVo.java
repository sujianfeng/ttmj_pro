package com.len.kindle.vo;

import lombok.*;

/**
 * @author sujianfeng
 * @date 2018/11/18 9:02 PM
 *
 * VIP统计
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class VipVo {
    private String d;
    private String channel;

    private int vip1User;
    private int vip1Jine;

    private int vip2User;
    private int vip2Jine;

    private int vip3User;
    private int vip3Jine;

    private int vip4User;
    private int vip4Jine;

    private int vip5User;
    private int vip5Jine;

    private int vip6User;
    private int vip6Jine;

    private int vip7User;
    private int vip7Jine;

    private int vip8User;
    private int vip8Jine;

    private int vip9User;
    private int vip9Jine;
}
