package com.len.kindle.entity;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * User entity.
 *
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "wealth_record")
@DynamicUpdate
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class WealthRecord extends BaseEntity {

    private Integer user_id;
    private Integer primary_room;
    private Integer primary_limit;
    private Integer primary_num;
    private Integer primary_rate;
    private Integer middle_room;
    private Integer middle_limit;
    private Integer middle_num;
    private Integer middle_rate;
    private Integer senior_room;
    private Integer senior_limit;
    private Integer senior_num;
    private Integer senior_rate;
    private Integer bsure;

}
