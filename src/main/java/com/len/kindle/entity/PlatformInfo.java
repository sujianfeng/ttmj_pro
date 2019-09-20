package com.len.kindle.entity;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author sujianfeng
 */
@Entity
@Table(name = "platform_info")
@DynamicUpdate
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class PlatformInfo extends BaseEntity {

    private int platform;
    private String platformName;

    private Date createTime;

}
