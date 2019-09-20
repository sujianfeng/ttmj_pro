package com.len.kindle.bean;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * @author sujianfeng
 * @date 2018/11/24 10:53 PM
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CardBean implements Serializable {
    private int g;
    private int mc;
    private int cc;
    private int d;
    private Date ct;
    private Date lt;
}
