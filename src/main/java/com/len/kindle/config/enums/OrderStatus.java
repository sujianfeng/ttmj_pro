package com.len.kindle.config.enums;

/**
 * @author sujianfeng
 */
public enum OrderStatus {
    /**
     *
     */
    CREATE(0),SUCCESS(1),FAIL(2);

    private int status;

    OrderStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
