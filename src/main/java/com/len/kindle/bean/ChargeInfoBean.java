package com.len.kindle.bean;

import lombok.*;

import java.io.Serializable;

/**
 * @author sujianfeng
 * @date 2018/11/21 12:29 AM
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ChargeInfoBean implements Serializable {
    //{"amount":1,"proId":1,"feedId":"000","pageId":"dailyPre","proType":4,"userId":13393,"itemId":0,"version":"1.0.0"}

    private int amount;
    private int proId;
    private String feedId;
    private String pageId;
    private int proType;
    private int userId;
    private int itemId;
    private String version;

    private String orderId;
    private int chargeType;

}
