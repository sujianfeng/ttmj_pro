package com.len.kindle.vo;

import lombok.*;

/**
 * @author sujianfeng
 * @date 2019-01-24 23:03
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class RealTimeDataVo {
    private String createTime;
    private int count1;
    private int count2;
    private int count3;
}
