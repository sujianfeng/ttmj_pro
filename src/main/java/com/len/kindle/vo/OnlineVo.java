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
public class OnlineVo {
    private int gameType;
    private String gameTypeName;
    private int count1;
    private int count2;
    private int count3;
    private int sumCount;
}
