package com.len.kindle.entity;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * @author sujianfeng
 */
@Entity
@Table(name = "channel_info")
@DynamicUpdate
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ChannelInfo extends BaseEntity {

    private int channel;
    private String channelName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "platformId")
    private PlatformInfo platform;

    private Date createTime;

}
