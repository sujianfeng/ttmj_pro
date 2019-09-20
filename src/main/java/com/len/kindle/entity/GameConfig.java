package com.len.kindle.entity;

import com.len.kindle.config.SystemData;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
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
@Table(name = "game_config")
@DynamicUpdate
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class GameConfig extends BaseEntity {

    private Integer type;
    private Integer platfrom;
    private Integer channel;
    private Integer priority;
    @Column(name = "[enable]")
    private Integer enable;
    @Column(name = "[on]")
    private Integer on;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    private String config;
    private String description;
    private Integer modifyID;
    private Date modifyTime;
    private Date createTime;

    @Transient
    private String typeName;

    public void setTypeName() {
        this.typeName = SystemData.CONFIGTYPEBEAN_MAP.get(this.type);
    }
}
