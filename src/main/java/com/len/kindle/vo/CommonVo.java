package com.len.kindle.vo;

import lombok.*;

/**
 * @author Administrator
 * @date 2018/12/3 10:29
 * <p>
 * 运营报表——充值付费
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CommonVo implements Comparable<CommonVo> {

    /**
     *   通用字段
     */

    private String dataDate;
    private String channel;

    /**
     * 充值付费
     */
    private int regCount;
    private int activeCount;
    private int totalFee;
    private int feeUser;
    private int feeCount;
    private double activeArpu;
    private double feeArpu;
    private double activeFeeRate;
    private double regFeeRate;

    /**
     * 项目数据
     */
    private long online;
    private long gemCount;
    private long tax;
    private long taifei;

    @Override
    public int compareTo(CommonVo o) {
        if (this.getDataDate().equals(o.getDataDate())) {
            return this.getChannel().compareTo(o.getChannel());
        } else {
            return this.getDataDate().compareTo(o.getDataDate());
        }
    }
}
