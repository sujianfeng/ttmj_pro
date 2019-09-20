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
@Table(name = "task_info")
@DynamicUpdate
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class TaskInfo extends BaseEntity {

    private Integer tid;
    private Integer type;
    private Integer need;
    private Integer gridType;
    private Integer img;
    private Integer finish;
    private Integer target;
    private Integer reached;
    private Integer gold;
    private String name;
    private String info;
    private String tip;
    private Date startTime;
    private Date endTime;
    private Date createTime;

}
