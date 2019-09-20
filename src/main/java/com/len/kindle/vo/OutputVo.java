package com.len.kindle.vo;

import lombok.*;

/**
 * @author sujianfeng
 * @date 2018/11/18 8:02 PM
 *
 * 产出统计
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class OutputVo {
    private String d;
    private String channel;

    private int mrdl;
    private int dtbcs;
    private int fjbcs;
    private int bdzh;
    private int pcbz;
    private int htsw;
    private int xthb;
    private int vip;
    private int th;
    private int sc;

}
