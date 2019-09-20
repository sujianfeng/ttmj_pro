package com.len.kindle.config.enums;

/**
 * @author sujianfeng
 * @date 2018/11/3 10:17 AM
 */
public enum PropType {

    /**
     * 无效的
     */
    EMPTY(0),
    /**
     * 支付购买所花费的金额，用于购买宝石，金币或捣蛋卡等道具
     */
    CASH(1),
    /**
     * 购买金币或捣蛋卡等道具
     */
    GEM(2),
    /**
     * 游戏中游戏输赢的评判
     */
    GOLD(3),
    /**
     * 奖励用户或抽奖奖品
     */
    PHONEBILL(4),
    /**
     * 游戏中查看别人牌
     */
    TOUSHI(5),
    /**
     * 游戏中给别人扔鸡蛋
     */
    DAODAN(8),
    /**
     * 充值套餐，给于一定的优惠
     */
    FENGCARD(10),
    /**
     * 充值套餐，给于比较大的优惠
     */
    LONGCARD(11),
    /**
     * 充值套餐，给于非常大的优惠
     */
    ZHIZHUNCARD(12);

    private int propType;

    PropType(int propType) {
        this.propType = propType;
    }

    public int getPropType() {
        return propType;
    }
}
