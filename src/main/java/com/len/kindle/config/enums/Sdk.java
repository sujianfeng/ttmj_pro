package com.len.kindle.config.enums;


/**
 * @author sujianfeng
 */

public enum Sdk {
    /**
     * OPPO
     */
    OPPO(1),
    /**
     * 九游
     */
    JIUYOU(2),

    /**
     * VIVO
     */
    VIVO(3),

    /**
     * huawei
     */
    HUAWEI(4),


    /**
     * xiaomi
     */
    XIAOMI(5),

    /**
     * weixin
     */
    WEIXIN(6),

    /**
     * weixin
     */
    ALIPAY(7);


    private Integer id;

    Sdk(int id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

}