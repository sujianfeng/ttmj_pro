package com.len.kindle.bean;

import lombok.*;

import java.io.Serializable;

/**
 * @author Administrator
 * @date 2018/11/20 15:16
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserBehaviorBean implements Serializable {
    private Integer userId;
    /**
     * suspend:禁止登录
     * silent:禁言
     * 0:不变，-1:解除禁止，1:禁止
     */
    private Integer suspend;
    private Integer silent;
}
