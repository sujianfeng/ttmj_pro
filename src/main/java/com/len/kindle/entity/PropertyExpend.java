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
@Table(name = "t_property_expend")
@DynamicUpdate
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class PropertyExpend extends BaseEntity {
    private Date d;
    private String channel;
    private long gemCount;
    private long goldCount;
    private long etCount;
    private long tsCount;
    private long zdCount;
}
