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
@Table(name = "bulletin_message")
@DynamicUpdate
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class BulletinMessage extends BaseEntity {

    private Integer popup;
    private String content;
    private Integer priority;
    private Integer enable;
    private Date startDate;
    private Date endDate;
    private Date startTime;
    private Date endTime;
    private Integer modifyID;
    private Date modifyTime;
    private Date createTime;

}
