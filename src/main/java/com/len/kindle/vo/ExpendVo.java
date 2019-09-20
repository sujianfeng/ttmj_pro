package com.len.kindle.vo;

import lombok.*;

/**
 * @author sujianfeng
 * @date 2018/11/18 11:39 PM
 * <p>
 * 玩牌统计
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ExpendVo implements Comparable<ExpendVo> {

    private String key;

    private String d;
    private String channel;

    private long gemInCount;
    private long gemOutCount;
    private long goldInCount;
    private long goldOutCount;
    private long ttInCount;
    private long ttOutCount;
    private long tsInCount;
    private long tsOutCount;
    private long ddInCount;
    private long ddOutCount;

    @Override
    public int compareTo(ExpendVo o) {
        if (this.getD().equals(o.getD())) {
            return this.getChannel().compareTo(o.getChannel());
        } else {
            return this.getD().compareTo(o.getD());
        }
    }
}