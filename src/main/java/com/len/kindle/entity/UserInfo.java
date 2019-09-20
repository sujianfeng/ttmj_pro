package com.len.kindle.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.len.kindle.bean.CardBean;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

/**
 * User entity.
 *
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user_info")
@DynamicUpdate
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserInfo extends BaseEntity {

    private String session;
    private String udid;
    private String account;
    private String password;
    private int permission;
    private String nickname;
    private String signature;
    private String headURL;
    private Integer sex;
    private Integer suspend;
    private Integer silent;
    private Integer userType;
    private Integer sysImageID;
    private Integer vipType;
    private Integer vipLevel;
    private Integer gem;
    private Integer gold;
    private Integer et;
    private Integer ts;
    private Integer zd;
    private Integer sp;
    private Integer qt;
    private Integer hp;
    private Integer loginDays;
    private Integer dailyPreCount;
    private Integer oldPlayer;
    private Integer registerUser;
    private Integer uploadImage;
    private Integer certification;
    private Integer rechargeAmount;
    private Date rechargeStartTime;
    private Integer monthRechargeAmount;
    private Integer chargeCount;
    private Date lastChargeTime;
    private Date lastDrewLotsTime;
    private Date lastLoginTime;
    private Date lastLoginAwardTime;
    private Date lastVipAwardTime;
    private Date lastDailyPreTime;
    private String taskStatus;
    private Integer tax;
    private Date subsidyTime;
    private Integer subsidyCount;
    private Integer platform;
    private Integer channel;
    private String version;
    private String fengCard;
    private String longCard;
    private String zhiZunCard;
    private String dzc;
    private String xlch;
    private String xzdd;
    private String ip;
    private Date createTime;
    private Date lastTime;

    @Transient
    private int fengCardInfo;
    @Transient
    private int longCardInfo;
    @Transient
    private int zhiZunCardInfo;


    @Transient
    private int lastdays;

    public void setLastdays() {
        int days = (int) ((System.currentTimeMillis() - lastChargeTime.getTime()) / (1000 * 3600 * 24)) - 1;
        this.lastdays = days;
    }

    public void setFengCardInfo() {
        this.fengCardInfo = setCardInfo(this.fengCard);
    }


    public void setLongCardInfo() {
        this.longCardInfo = setCardInfo(this.longCard);
    }

    public void setZhiZunCardInfo() {
        this.zhiZunCardInfo = setCardInfo(this.zhiZunCard);
    }

    private int setCardInfo(String card) {
        if (StringUtils.isBlank(card)) {
            return 0;
        }

        ObjectMapper mapper = new ObjectMapper();
        try {
            CardBean cardBean = mapper.readValue(card, CardBean.class);
            int days = (int) ((System.currentTimeMillis() - cardBean.getCt().getTime()) / (1000 * 3600 * 24)) - 1;
            return days >= cardBean.getD() ? 0 : cardBean.getD() - days;
        } catch (Exception e) {
            e.printStackTrace();
        }


        return 0;
    }
}
