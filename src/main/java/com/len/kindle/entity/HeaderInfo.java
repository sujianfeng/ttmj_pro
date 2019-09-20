package com.len.kindle.entity;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author sujianfeng
 */
@Entity
@Table(name = "header_info")
@DynamicUpdate
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class HeaderInfo extends BaseEntity {
    
    private String userid;
    private String dir;
    private String curImg;
    private String curImgSize;
    private String curImgTime;
    private String uploadImg;
    private String uploadImgSize;
    private String uploadImgTime;
    private Integer status;

}
