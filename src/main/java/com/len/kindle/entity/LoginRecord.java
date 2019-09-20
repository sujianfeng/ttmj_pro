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
@Table(name = "login_record")
@DynamicUpdate
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class LoginRecord extends BaseEntity {

    private Integer userID;
    private Integer platform;
    private Integer channel;
    private Integer gameType;
    private Integer gameID;
    private Integer gameLevel;
    private Integer status;
    private String ip;
    private Date createTime;
}
