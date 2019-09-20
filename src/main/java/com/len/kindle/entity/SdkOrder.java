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
@Table(name = "sdk_order")
@DynamicUpdate
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class SdkOrder extends BaseEntity {

    private Integer sdkId;
    private String sdkOrderId;
    private String orderId;
    private String productName;
    private String productDesc;
    private Integer price;
    private Integer count;
    private String attach;
    private String sign;
    private String productId;
    private Integer payType;
    private Integer orderStatus;
    private String orderMsg;
    private Date orderTime;
    private Date createTime;

}
