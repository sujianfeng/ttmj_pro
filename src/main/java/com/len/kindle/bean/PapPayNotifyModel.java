package com.len.kindle.bean;

import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author pk
 * @since 2016/11/25.
 */
@XmlRootElement(name = "xml")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class PapPayNotifyModel {
    private String return_code;
    private String return_msg;
    private String appid;
    private String mch_id;
    private String nonce_str;
    private String sign;
    private String result_code;
    private String openid;

    private String attach;

    private String trade_state;
    private String is_subscribe;
    private String trade_type;
    private String bank_type;
    private int total_fee;
    private String cash_fee;
    private String transaction_id;
    private String out_trade_no;
    private String time_end;
    private String err_code;
    private String err_code_des;


    private String contract_code;
    private String plan_id;
    private String change_type;
    private String operate_time;
    private String contract_id;
    private String contract_expired_time;
    private int contract_termination_mode;
    private String request_serial;

}
