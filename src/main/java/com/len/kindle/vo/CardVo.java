package com.len.kindle.vo;

import lombok.*;

/**
 * @author sujianfeng
 * @date 2018/11/18 8:02 PM
 *
 * 龙凤至尊卡统计
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CardVo {
    private String d;
    private String channel;

    private int fengUser;
    private int fengCount;
    private int fengJine;

    private int longUser;
    private int longCount;
    private int longJine;


    private int zhizhunUser;
    private int zhizhunCount;
    private int zhizhunJine;
}
