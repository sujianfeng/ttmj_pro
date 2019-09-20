package com.len.kindle.entity;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author sujianfeng
 */
@Entity
@Table(name = "goods_info")
@DynamicUpdate
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class GoodsInfo extends BaseEntity {

    private Integer goodsId;
    private String goodsType;
    private String goodsName;
    private String goodsDesc;
    private Integer price;
    private Integer cardCount;
    private String cardDesc;
    private String linkUrl;
    private String modifyTime;
    private String picPath;

}
