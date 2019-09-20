package com.len.kindle.bean;

import lombok.*;

import java.io.Serializable;

/**
 * @author Administrator
 * @date 2018/11/13 16:19
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ResultBean implements Serializable {
    private Integer status;
    private Integer result;
    private Integer errcode;
    private String errMsg;
}
