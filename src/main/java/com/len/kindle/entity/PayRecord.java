package com.len.kindle.entity;

import com.len.kindle.config.SystemData;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

/**
 * User entity.
 *
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "pay_record")
@DynamicUpdate
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class PayRecord extends BaseEntity {

    private String orderID;
    private Integer userID;
    private Integer vip;
    private Integer platform;
    private Integer channel;
    private Integer chargeType;
    private Integer type;
    private Integer producer;
    private Integer prop;
    private Integer amount;
    private Integer total;
    private Date createTime;

    @Transient
    private String propName;
    @Transient
    private String typeName;
    @Transient
    private String producerName;
    @Transient
    private Integer beforeChange;

    public void setBeforeChange() {
        this.beforeChange = this.total - this.amount;
    }

    public void setPropName() {
        this.propName = SystemData.PROP_MAP.get(this.prop);
    }

    public void setTypeName() {
        this.typeName = SystemData.TYPE_MAP.get(this.type);
    }

    public void setProducerName() {
        this.producerName = SystemData.PRODUCER_MAP.get(this.producer);
    }
}
