package com.len.kindle.config.enums;

/**
 * @author sujianfeng
 * @date 2018/11/3 10:26 AM
 */
public enum RecordType {
    /**
     * 无效的
     */
    EMPTY(0),
    /**
     * 当条记录是支付记录，是用金额去购买，也包括所购买的具体商品
     */
    PAY(1),
    /**
     * 购买是指用宝石去购买金币或其它道具
     */
    BUY(2),
    /**
     * 充值或购买时所赠送的，也有系统赠送，抽奖等
     */
    GIFT(3),
    /**
     * 游戏中输赢的金币数量
     */
    WINLOSE(4),
    /**
     * 游戏中嬴后按比例所扣除的税额,值为正
     */
    TAX(5),
    /**
     * 抽奖所获取的话费卡兑换成话费
     */
    EXCHANGE(6),
    /**
     * 拜财神所需要的金币数量
     */
    BAICAISHEN(7),
    /**
     * 游戏中玩家所消耗的道具
     */
    CONSUME(8),
    /**
     * 在游戏中玩家拜财神所获得的奖励
     */
    CAISHENREWARD(9),
    /**
     * 玩家玩一局游戏所扣除的费用
     */
    TAIFEI(10);

    private int recordType;

    RecordType(int recordType) {
        this.recordType = recordType;
    }

    public int getRecordType() {
        return recordType;
    }
}
