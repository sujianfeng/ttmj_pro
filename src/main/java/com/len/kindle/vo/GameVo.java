package com.len.kindle.vo;

import lombok.*;

/**
 * @author sujianfeng
 * @date 2018/11/18 11:39 PM
 *
 * 玩牌统计
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class GameVo {

    private String d;
    private String channel;

    private String gametype;
    private int peoplecount;
    private int gamecount;

    private int roomcount;
}