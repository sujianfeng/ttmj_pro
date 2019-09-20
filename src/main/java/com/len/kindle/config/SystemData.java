package com.len.kindle.config;

import com.len.kindle.bean.ConfigTypeBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author sujianfeng
 * @date 2018/11/21 1:50 AM
 */
public class SystemData {

    public static Map<Integer, String> TYPE_MAP = new ConcurrentHashMap<>();
    public static Map<Integer, String> PROP_MAP = new ConcurrentHashMap<>();
    public static Map<Integer, String> PRODUCER_MAP = new ConcurrentHashMap<>();
    private static List<ConfigTypeBean> CONFIGTYPEBEAN_LIST = new ArrayList<>();
    public static Map<Integer, String> CONFIGTYPEBEAN_MAP = new ConcurrentHashMap<>();
    public static Map<Integer, String> GAMETYPE_MAP = new ConcurrentHashMap<>();
    public static Map<Integer, String> GAMELEVEL_MAP = new ConcurrentHashMap<>();



    static {
        GAMELEVEL_MAP.put(0, "大厅");
        GAMELEVEL_MAP.put(1, "初级场");
        GAMELEVEL_MAP.put(2, "中级场");
        GAMELEVEL_MAP.put(3, "高级场");


        GAMETYPE_MAP.put(0, "二人场");
        GAMETYPE_MAP.put(3, "推筒子");
        GAMETYPE_MAP.put(5, "仙人夺宝");
        GAMETYPE_MAP.put(6, "血流成河");
        GAMETYPE_MAP.put(7, "豹子王");
        GAMETYPE_MAP.put(9, "血战到底");
        GAMETYPE_MAP.put(1000, "大厅");


        TYPE_MAP.put(0, "空的");
        TYPE_MAP.put(1, "支付");
        TYPE_MAP.put(2, "购买");
        TYPE_MAP.put(3, "赠送");
        TYPE_MAP.put(4, "输赢");
        TYPE_MAP.put(5, "扣税");
        TYPE_MAP.put(6, "兑换");
        TYPE_MAP.put(7, "拜财神");
        TYPE_MAP.put(8, "消耗道具");
        TYPE_MAP.put(9, "拜财神奖励");
        TYPE_MAP.put(10, "台费");
        TYPE_MAP.put(11, "手动调整");


        PROP_MAP.put(0, "空的");
        PROP_MAP.put(1, "金额");
        PROP_MAP.put(2, "宝石");
        PROP_MAP.put(3, "金币");
        PROP_MAP.put(4, "话费");
        PROP_MAP.put(5, "透视卡");
        PROP_MAP.put(8, "捣蛋卡");
        PROP_MAP.put(10, "凤卡");
        PROP_MAP.put(11, "龙卡");
        PROP_MAP.put(12, "至尊卡");


        //大厅
        PRODUCER_MAP.put(101, "商品购买页面，欢乐商城->金币");
        PRODUCER_MAP.put(102, "抽签，用户花费宝石抽签也算是赠送");
        PRODUCER_MAP.put(103, "每日登录奖励，七天循环");
        PRODUCER_MAP.put(104, "上传头像奖励");
        PRODUCER_MAP.put(105, "VIP用户每日领取奖励");
        PRODUCER_MAP.put(106, "普通玩家破产补助");
        PRODUCER_MAP.put(107, "凤卡奖励");
        PRODUCER_MAP.put(108, "龙卡奖励");
        PRODUCER_MAP.put(109, "至尊卡奖励");
        PRODUCER_MAP.put(110, "其它奖励");
        PRODUCER_MAP.put(111, "未知的充值来源");
        PRODUCER_MAP.put(112, "商品购买充值，欢乐商城->金币，无宝石时转充值页面");
        PRODUCER_MAP.put(113, "宝石购买充值，欢乐商城->宝石");
        PRODUCER_MAP.put(114, "首充充值");
        PRODUCER_MAP.put(115, "每日特惠充值");
        PRODUCER_MAP.put(116, "凤卡充值");
        PRODUCER_MAP.put(117, "龙卡充值");
        PRODUCER_MAP.put(118, "至尊卡充值");
        PRODUCER_MAP.put(119, "游戏中充值");
        PRODUCER_MAP.put(120, "兑换话费");

        //推筒子记录来源
        PRODUCER_MAP.put(201, "推筒子快速购买金币");
        PRODUCER_MAP.put(202, "推筒子快速购买金币，无宝石时充值对应宝石");
        PRODUCER_MAP.put(203, "推筒子发红包");
        PRODUCER_MAP.put(204, "推筒子领红包");
        PRODUCER_MAP.put(205, "推筒子普通玩家跟系统庄家的输赢");
        PRODUCER_MAP.put(206, "推筒子普通玩家跟玩家做庄的输赢");
        PRODUCER_MAP.put(207, "推筒子玩家做庄的输赢");


        //二人场记录来源
        PRODUCER_MAP.put(401, "初级二人场充值金币");
        PRODUCER_MAP.put(402, "初级二人场充值道具");
        PRODUCER_MAP.put(403, "初级二人场系统红包");
        PRODUCER_MAP.put(404, "初级二人场玩家领取红包");
        PRODUCER_MAP.put(405, "初级二人场玩家输赢金币");
        PRODUCER_MAP.put(406, "初级二人场使用道具");
        PRODUCER_MAP.put(407, "初级二人场扣税");
        PRODUCER_MAP.put(408, "初级二人场拜财神");
        PRODUCER_MAP.put(409, "初级二人场博饼奖励金币");
        PRODUCER_MAP.put(410, "初级二人场博饼奖励道具");
        PRODUCER_MAP.put(411, "初级二人场台费");
        PRODUCER_MAP.put(412, "初级二人场游戏中领取普通用户破产补助");
        PRODUCER_MAP.put(413, "初级二人场游戏中领取凤卡奖励");
        PRODUCER_MAP.put(414, "初级二人场游戏中领取龙卡奖励");
        PRODUCER_MAP.put(415, "初级二人场游戏中领取至尊卡奖励");

        PRODUCER_MAP.put(501, "中级二人场充值金币");
        PRODUCER_MAP.put(502, "中级二人场充值道具");
        PRODUCER_MAP.put(503, "中级二人场系统红包");
        PRODUCER_MAP.put(504, "中级二人场玩家领取红包");
        PRODUCER_MAP.put(505, "中级二人场玩家输赢金币");
        PRODUCER_MAP.put(506, "中级二人场使用道具");
        PRODUCER_MAP.put(507, "中级二人场扣税");
        PRODUCER_MAP.put(508, "中级二人场拜财神");
        PRODUCER_MAP.put(509, "中级二人场博饼奖励金币");
        PRODUCER_MAP.put(510, "中级二人场博饼奖励道具");
        PRODUCER_MAP.put(511, "中级二人场台费");
        PRODUCER_MAP.put(512, "中级二人场游戏中领取普通用户破产补助");
        PRODUCER_MAP.put(513, "中级二人场游戏中领取凤卡奖励");
        PRODUCER_MAP.put(514, "中级二人场游戏中领取龙卡奖励");
        PRODUCER_MAP.put(515, "中级二人场游戏中领取至尊卡奖励");

        PRODUCER_MAP.put(601, "高级二人场充值金币");
        PRODUCER_MAP.put(602, "高级二人场充值道具");
        PRODUCER_MAP.put(603, "高级二人场系统红包");
        PRODUCER_MAP.put(604, "高级二人场玩家领取红包");
        PRODUCER_MAP.put(605, "高级二人场玩家输赢金币");
        PRODUCER_MAP.put(606, "高级二人场使用道具");
        PRODUCER_MAP.put(607, "高级二人场扣税");
        PRODUCER_MAP.put(608, "高级二人场拜财神");
        PRODUCER_MAP.put(609, "高级二人场博饼奖励金币");
        PRODUCER_MAP.put(610, "高级二人场博饼奖励道具");
        PRODUCER_MAP.put(611, "高级二人场台费");
        PRODUCER_MAP.put(612, "高级二人场游戏中领取普通用户破产补助");
        PRODUCER_MAP.put(613, "高级二人场游戏中领取凤卡奖励");
        PRODUCER_MAP.put(614, "高级二人场游戏中领取龙卡奖励");
        PRODUCER_MAP.put(615, "高级二人场游戏中领取至尊卡奖励");

        //血战到底场记录来源
        PRODUCER_MAP.put(701, "初级血战到底充值金币");
        PRODUCER_MAP.put(702, "初级血战到底充值道具");
        PRODUCER_MAP.put(703, "初级血战到底系统红包");
        PRODUCER_MAP.put(704, "初级血战到底玩家领取红包");
        PRODUCER_MAP.put(705, "初级血战到底玩家输赢金币");
        PRODUCER_MAP.put(706, "初级血战到底使用道具");
        PRODUCER_MAP.put(707, "初级血战到底扣税");
        PRODUCER_MAP.put(708, "初级血战到底拜财神");
        PRODUCER_MAP.put(709, "初级血战到底博饼奖励金币");
        PRODUCER_MAP.put(710, "初级血战到底博饼奖励道具");
        PRODUCER_MAP.put(711, "初级血战到底台费");
        PRODUCER_MAP.put(712, "初级血战到底游戏中领取普通用户破产补助");
        PRODUCER_MAP.put(713, "初级血战到底游戏中领取凤卡奖励");
        PRODUCER_MAP.put(714, "初级血战到底游戏中领取龙卡奖励");
        PRODUCER_MAP.put(715, "初级血战到底游戏中领取至尊卡奖励");

        PRODUCER_MAP.put(801, "中级血战到底充值金币");
        PRODUCER_MAP.put(802, "中级血战到底充值道具");
        PRODUCER_MAP.put(803, "中级血战到底系统红包");
        PRODUCER_MAP.put(804, "中级血战到底玩家领取红包");
        PRODUCER_MAP.put(805, "中级血战到底玩家输赢金币");
        PRODUCER_MAP.put(806, "中级血战到底使用道具");
        PRODUCER_MAP.put(807, "中级血战到底扣税");
        PRODUCER_MAP.put(808, "中级血战到底拜财神");
        PRODUCER_MAP.put(809, "中级血战到底博饼奖励金币");
        PRODUCER_MAP.put(810, "中级血战到底博饼奖励道具");
        PRODUCER_MAP.put(811, "中级血战到底台费");
        PRODUCER_MAP.put(812, "中级血战到底游戏中领取普通用户破产补助");
        PRODUCER_MAP.put(813, "中级血战到底游戏中领取凤卡奖励");
        PRODUCER_MAP.put(814, "中级血战到底游戏中领取龙卡奖励");
        PRODUCER_MAP.put(815, "中级血战到底游戏中领取至尊卡奖励");

        PRODUCER_MAP.put(901, "高级血战到底充值金币");
        PRODUCER_MAP.put(902, "高级血战到底充值道具");
        PRODUCER_MAP.put(903, "高级血战到底系统红包");
        PRODUCER_MAP.put(904, "高级血战到底玩家领取红包");
        PRODUCER_MAP.put(905, "高级血战到底玩家输赢金币");
        PRODUCER_MAP.put(906, "高级血战到底使用道具");
        PRODUCER_MAP.put(907, "高级血战到底扣税");
        PRODUCER_MAP.put(908, "高级血战到底拜财神");
        PRODUCER_MAP.put(909, "高级血战到底博饼奖励金币");
        PRODUCER_MAP.put(910, "高级血战到底博饼奖励道具");
        PRODUCER_MAP.put(911, "高级血战到底台费");
        PRODUCER_MAP.put(912, "高级血战到底游戏中领取普通用户破产补助");
        PRODUCER_MAP.put(913, "高级血战到底游戏中领取凤卡奖励");
        PRODUCER_MAP.put(914, "高级血战到底游戏中领取龙卡奖励");
        PRODUCER_MAP.put(915, "高级血战到底游戏中领取至尊卡奖励");


        //血流成河记录来源
        PRODUCER_MAP.put(1001, "初级血流成河充值金币");
        PRODUCER_MAP.put(1002, "初级血流成河充值道具");
        PRODUCER_MAP.put(1003, "初级血流成河系统红包");
        PRODUCER_MAP.put(1004, "初级血流成河玩家领取红包");
        PRODUCER_MAP.put(1005, "初级血流成河玩家输赢金币");
        PRODUCER_MAP.put(1006, "初级血流成河使用道具");
        PRODUCER_MAP.put(1007, "初级血流成河扣税");
        PRODUCER_MAP.put(1008, "初级血流成河拜财神");
        PRODUCER_MAP.put(1009, "初级血流成河博饼奖励金币");
        PRODUCER_MAP.put(1010, "初级血流成河博饼奖励道具");
        PRODUCER_MAP.put(1011, "初级血流成河台费");
        PRODUCER_MAP.put(1012, "初级血流成河游戏中领取普通用户破产补助");
        PRODUCER_MAP.put(1013, "初级血流成河游戏中领取凤卡奖励");
        PRODUCER_MAP.put(1014, "初级血流成河游戏中领取龙卡奖励");
        PRODUCER_MAP.put(1015, "初级血流成河游戏中领取至尊卡奖励");

        PRODUCER_MAP.put(1101, "中级血流成河充值金币");
        PRODUCER_MAP.put(1102, "中级血流成河充值道具");
        PRODUCER_MAP.put(1103, "中级血流成河系统红包");
        PRODUCER_MAP.put(1104, "中级血流成河玩家领取红包");
        PRODUCER_MAP.put(1105, "中级血流成河玩家输赢金币");
        PRODUCER_MAP.put(1106, "中级血流成河使用道具");
        PRODUCER_MAP.put(1107, "中级血流成河扣税");
        PRODUCER_MAP.put(1108, "中级血流成河拜财神");
        PRODUCER_MAP.put(1109, "中级血流成河博饼奖励金币");
        PRODUCER_MAP.put(1110, "中级血流成河博饼奖励道具");
        PRODUCER_MAP.put(1111, "中级血流成河台费");
        PRODUCER_MAP.put(1112, "中级血流成河游戏中领取普通用户破产补助");
        PRODUCER_MAP.put(1113, "中级血流成河游戏中领取凤卡奖励");
        PRODUCER_MAP.put(1114, "中级血流成河游戏中领取龙卡奖励");
        PRODUCER_MAP.put(1115, "中级血流成河游戏中领取至尊卡奖励");

        PRODUCER_MAP.put(1201, "高级血流成河充值金币");
        PRODUCER_MAP.put(1202, "高级血流成河充值道具");
        PRODUCER_MAP.put(1203, "高级血流成河系统红包");
        PRODUCER_MAP.put(1204, "高级血流成河玩家领取红包");
        PRODUCER_MAP.put(1205, "高级血流成河玩家输赢金币");
        PRODUCER_MAP.put(1206, "高级血流成河使用道具");
        PRODUCER_MAP.put(1207, "高级血流成河扣税");
        PRODUCER_MAP.put(1208, "高级血流成河拜财神");
        PRODUCER_MAP.put(1209, "高级血流成河博饼奖励金币");
        PRODUCER_MAP.put(1210, "高级血流成河博饼奖励道具");
        PRODUCER_MAP.put(1211, "高级血流成河台费");
        PRODUCER_MAP.put(1212, "高级血流成河游戏中领取普通用户破产补助");
        PRODUCER_MAP.put(1213, "高级血流成河游戏中领取凤卡奖励");
        PRODUCER_MAP.put(1214, "高级血流成河游戏中领取龙卡奖励");
        PRODUCER_MAP.put(1215, "高级血流成河游戏中领取至尊卡奖励");


        //后台调整
        PRODUCER_MAP.put(1301, "后台调整");

        CONFIGTYPEBEAN_MAP.put(0, "无效");
        CONFIGTYPEBEAN_MAP.put(1, "抽签");
        CONFIGTYPEBEAN_MAP.put(2, "登录模式");
        CONFIGTYPEBEAN_MAP.put(3, "新用户注册初始值");
        CONFIGTYPEBEAN_MAP.put(4, "每日特惠");
        CONFIGTYPEBEAN_MAP.put(5, "首充优惠");
        CONFIGTYPEBEAN_MAP.put(6, "充值配置");
        CONFIGTYPEBEAN_MAP.put(7, "每日登录奖励");
        CONFIGTYPEBEAN_MAP.put(8, "话费领取配置");
        CONFIGTYPEBEAN_MAP.put(9, "二人对战初级场");
        CONFIGTYPEBEAN_MAP.put(10, "二人对战中级场");
        CONFIGTYPEBEAN_MAP.put(11, "二人对战高级场");
        CONFIGTYPEBEAN_MAP.put(12, "血流成河初级场");
        CONFIGTYPEBEAN_MAP.put(13, "血流成河中级场");
        CONFIGTYPEBEAN_MAP.put(14, "血流成河高级场");
        CONFIGTYPEBEAN_MAP.put(15, "血战到底初级场");
        CONFIGTYPEBEAN_MAP.put(16, "血战到底中级场");
        CONFIGTYPEBEAN_MAP.put(17, "血战到底高级场");
        CONFIGTYPEBEAN_MAP.put(18, "推筒子普通场");
        CONFIGTYPEBEAN_MAP.put(19, "推筒子富人场");
        CONFIGTYPEBEAN_MAP.put(20, "商城配置");
        CONFIGTYPEBEAN_MAP.put(21, "龙凤至尊卡配置");
        CONFIGTYPEBEAN_MAP.put(22, "VIP等级划分条件");
        CONFIGTYPEBEAN_MAP.put(23, "基本通用配置");
        CONFIGTYPEBEAN_MAP.put(24, "微信识别信息");
        CONFIGTYPEBEAN_MAP.put(25, "功能开关");
        CONFIGTYPEBEAN_MAP.put(26, "大喇叭全局配置");



    }

    public static List<ConfigTypeBean> reInitConfigTypeList() {
        CONFIGTYPEBEAN_LIST.clear();
        //游戏配置类型
        CONFIGTYPEBEAN_LIST.add(ConfigTypeBean.builder().type(0).typeName("无效").build());
        CONFIGTYPEBEAN_LIST.add(ConfigTypeBean.builder().type(1).typeName("抽签").build());
        CONFIGTYPEBEAN_LIST.add(ConfigTypeBean.builder().type(2).typeName("登录模式").build());
        CONFIGTYPEBEAN_LIST.add(ConfigTypeBean.builder().type(3).typeName("新用户注册初始值").build());
        CONFIGTYPEBEAN_LIST.add(ConfigTypeBean.builder().type(4).typeName("每日特惠").build());
        CONFIGTYPEBEAN_LIST.add(ConfigTypeBean.builder().type(5).typeName("首充优惠").build());
        CONFIGTYPEBEAN_LIST.add(ConfigTypeBean.builder().type(6).typeName("充值配置").build());
        CONFIGTYPEBEAN_LIST.add(ConfigTypeBean.builder().type(7).typeName("每日登录奖励").build());
        CONFIGTYPEBEAN_LIST.add(ConfigTypeBean.builder().type(8).typeName("话费领取配置").build());
        CONFIGTYPEBEAN_LIST.add(ConfigTypeBean.builder().type(9).typeName("二人对战初级场").build());
        CONFIGTYPEBEAN_LIST.add(ConfigTypeBean.builder().type(10).typeName("二人对战中级场").build());
        CONFIGTYPEBEAN_LIST.add(ConfigTypeBean.builder().type(11).typeName("二人对战高级场").build());
        CONFIGTYPEBEAN_LIST.add(ConfigTypeBean.builder().type(12).typeName("血流成河初级场").build());
        CONFIGTYPEBEAN_LIST.add(ConfigTypeBean.builder().type(13).typeName("血流成河中级场").build());
        CONFIGTYPEBEAN_LIST.add(ConfigTypeBean.builder().type(14).typeName("血流成河高级场").build());
        CONFIGTYPEBEAN_LIST.add(ConfigTypeBean.builder().type(15).typeName("血战到底初级场").build());
        CONFIGTYPEBEAN_LIST.add(ConfigTypeBean.builder().type(16).typeName("血战到底中级场").build());
        CONFIGTYPEBEAN_LIST.add(ConfigTypeBean.builder().type(17).typeName("血战到底高级场").build());
        CONFIGTYPEBEAN_LIST.add(ConfigTypeBean.builder().type(18).typeName("推筒子普通场").build());
        CONFIGTYPEBEAN_LIST.add(ConfigTypeBean.builder().type(19).typeName("推筒子富人场").build());
        CONFIGTYPEBEAN_LIST.add(ConfigTypeBean.builder().type(20).typeName("商城配置").build());
        CONFIGTYPEBEAN_LIST.add(ConfigTypeBean.builder().type(21).typeName("龙凤至尊卡配置").build());
        CONFIGTYPEBEAN_LIST.add(ConfigTypeBean.builder().type(22).typeName("VIP等级划分条件").build());
        CONFIGTYPEBEAN_LIST.add(ConfigTypeBean.builder().type(23).typeName("基本通用配置").build());
        CONFIGTYPEBEAN_LIST.add(ConfigTypeBean.builder().type(24).typeName("微信识别信息").build());
        CONFIGTYPEBEAN_LIST.add(ConfigTypeBean.builder().type(25).typeName("功能开关配置").build());
        CONFIGTYPEBEAN_LIST.add(ConfigTypeBean.builder().type(26).typeName("大喇叭全局配置").build());

        return CONFIGTYPEBEAN_LIST;
    }

}
