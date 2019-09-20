package com.len.kindle.bean;

import lombok.*;

import java.io.Serializable;

/**
 * @author Administrator
 * @date 2018/11/9 10:27
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class VivoPraPayResultBean implements Serializable {
    private String respCode;
    private String respMsg;
    private String signMethod;
    private String signature;
    private String accessKey;
    private String orderNumber;
    private String orderAmount;
    private String cpOrderNumer;
}
