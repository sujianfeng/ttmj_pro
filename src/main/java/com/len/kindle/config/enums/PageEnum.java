package com.len.kindle.config.enums;

/**
 * @author len
 * @date 2016/12/16
 */
public enum PageEnum {
    /**
     * 登陆页面
     */
    PAGE_LOGIN("login"),
    PAGE_INDEX("index"),
    PAGE_USERLIST("user_list"),
    PAGE_EDIT_USERINFO("user_edit"),
    PAGE_PRODUCTLIST("product_list"),
    PAGE_EDIT_PRODUCTINFO("product_edit"),
    PAGE_DATALIST("data_list"),
    PAGE_IMPORTFAIL("import_fail"),
    PAGE_IMPORTSUCC("import_succ"),
    PAGE_CHANNELDATALIST("data_channel_list"),
    PAGE_DATASUMMARYLIST("data_summary_list"),
    //tableinfo
    PAGE_TABLEINFO_GAMERECORDLIST("tableinfo/gamerecord_list"),
    PAGE_TABLEINFO_LOGINRECORDLIST("tableinfo/loginrecord_list"),
    PAGE_TABLEINFO_NICKNAMECONFIGLIST("tableinfo/nicknameconfig_list"),
    PAGE_TABLEINFO_USERINFOLIST("tableinfo/userinfo_list"),
    PAGE_TABLEINFO_PROPERTYRECORDLIST("tableinfo/propertyrecord_list"),
    PAGE_TABLEINFO_SERVERCONFIGLIST("tableinfo/serverconfig_list"),
    PAGE_TABLEINFO_HEADINFOLIST("tableinfo/headerinfo_list"),

    //operatingreport
    PAGE_OR_PAYINFO("operatingreport/payinfo"),
    PAGE_OR_USERRETENTION("operatingreport/userretention"),
    PAGE_OR_REALTIMEDATA("operatingreport/realtimedata"),
    PAGE_OR_PROJECTDATA("operatingreport/projectdata"),
    PAGE_OR_REALTIMEPUMPING("operatingreport/realtimepumping"),
    PAGE_OR_INVENTORY("operatingreport/inventory"),
    PAGE_OR_EXPEND("operatingreport/expend"),
    PAGE_OR_CHANNELPROJECT("operatingreport/channelproject"),
    PAGE_OR_ONLINE("operatingreport/online"),
    PAGE_OR_CONFIG("operatingreport/config"),
    PAGE_OR_PLATFORM_EDIT("operatingreport/platform_edit"),
    PAGE_OR_CHANNEL_LIST("operatingreport/channellist"),
    PAGE_OR_CHANNEL_EDIT("operatingreport/channel_edit"),
    PAGE_OR_GAMECONFIG_EDIT("operatingreport/gameconfig_edit"),

    //useranalysis
    PAGE_UA_PLAYCARDS("useranalysis/playcards"),
    PAGE_UA_RECHARGE("useranalysis/recharge"),
    PAGE_UA_RECHARGE_DETAIL("useranalysis/recharge_detail"),
    PAGE_UA_RMBUSER("useranalysis/rmbuser"),
    PAGE_UA_PROP("useranalysis/prop"),

    //backtransaction
    PAGE_BT_GOLD("backtransaction/gold"),
    PAGE_BT_GEM("backtransaction/gem"),


    //customerservice
    PAGE_CS_SYSTEMMESSAGE("customerservice/sysmsg"),
    PAGE_CS_EXCHANGE("customerservice/exchange"),
    PAGE_CS_SUSPEND("customerservice/suspend"),
    PAGE_CS_SILENT("customerservice/silent"),
    PAGE_EDIT_CS_SYSTEMMESSAGE("customerservice/sysmsg_edit"),
    PAGE_CS_USERMESSAGE("customerservice/usermsg"),


    //systemconfig
    PAGE_SC_PRIVILEGES("systemconfig/privileges"),
    PAGE_SC_CHANNELUSER("systemconfig/channeluser"),


    //productreport
    PAGE_PR_LFZCARD("productreport/lfzcard"),
    PAGE_PR_VIP("productreport/vip"),
    PAGE_PR_OUTPUT("productreport/output"),
    PAGE_PR_GIFT("productreport/gift");


    private String page;

    PageEnum(String page) {
        this.page = page;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return getPage();
    }
}
