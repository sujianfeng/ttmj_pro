package com.len.kindle.config.enums;

/**
 * @author sujianfeng
 */

public enum PayType {
    /**
     * 未知
     */
    UNKNOWN(0),
    /**
     * 移动
     */
    CMCC(1),
    /**
     * 联通
     */
    UNICOM(2),
    /**
     * 电信
     */
    TELECOM(3),
    /**
     * UC
     */
    UC(4),
    /**
     * 支付宝
     */
    ALI(5),
    /**
     * 微信
     */
    WECHAT(6);

    private int payType;

    PayType(int payType) {
        this.payType = payType;
    }

    public int getPayType() {
        return payType;
    }
}
