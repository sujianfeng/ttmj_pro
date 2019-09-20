package com.len.kindle.bean;

import lombok.*;

import java.io.Serializable;

/**
 * @author Administrator
 * @date 2018/11/13 15:45
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserInfoBean  implements Serializable {
    private Integer userId;
    private Integer gem;
    private Integer gold;
    private Integer et;
    private Integer ts;
    private Integer zd;

}
