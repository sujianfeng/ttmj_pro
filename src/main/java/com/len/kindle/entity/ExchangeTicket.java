package com.len.kindle.entity;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * User entity.
 *
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "exchange_ticket")
@DynamicUpdate
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ExchangeTicket extends BaseEntity {

    private Integer userID;
    private Integer vip;
    private Integer platform;
    private Integer channel;
    private Integer amount;
    private Integer goodsID;
    private String goodsName;
    private String name;
    private String tel;
    private String addr;
    private Integer status;
    private Integer exchangeID;
    private Date createTime;
    private Date exchangeTime;
}
