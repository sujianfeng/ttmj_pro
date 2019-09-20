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
@Table(name = "activity_info")
@DynamicUpdate
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ActivityInfo extends BaseEntity {

    private Integer state;
    private Integer type;
    private Integer propType;
    private Integer propCount;
    private String title;
    private String info;
    private String detail;
    private Date startTime;
    private Date endTime;
    private Date createTime;

}
