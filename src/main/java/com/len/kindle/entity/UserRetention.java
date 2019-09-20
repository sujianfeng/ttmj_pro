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
@Table(name = "t_user_retention")
@DynamicUpdate
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserRetention extends BaseEntity {
    private Date d;
    private String channel;
    private Integer user_reg;
    private Integer retention_2;
    private Integer retention_3;
    private Integer retention_4;
    private Integer retention_5;
    private Integer retention_6;
    private Integer retention_7;
    private Integer retention_14;
    private Integer retention_30;
    private Integer retention_60;
    private Integer retention_90;
}
