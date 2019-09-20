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
@Table(name = "online_record")
@DynamicUpdate
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class OnlineRecord extends BaseEntity {

    private Integer platform;
    private Integer channel;
    private Integer gameType;
    private Integer gameID;
    private Integer gameLevel;
    private Integer count;
    private Date createTime;
}
