package com.len.kindle.bean;

import lombok.*;

/**
 * @author sujianfeng
 * @date 2019-03-17 15:13
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class GameBean {

    private int gameId;
    private String gameName;

}
