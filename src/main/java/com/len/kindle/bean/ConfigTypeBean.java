package com.len.kindle.bean;

import lombok.*;

import java.io.Serializable;

/**
 * @author sujianfeng
 * @date 2018-12-09 15:01
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ConfigTypeBean implements Serializable {

    private int type;
    private String typeName;
}
