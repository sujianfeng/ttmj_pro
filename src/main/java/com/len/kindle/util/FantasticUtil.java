package com.len.kindle.util;


import lombok.extern.slf4j.Slf4j;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 会重复使用的工具函数
 *
 * @author pk
 * @since 2017/3/15.
 */
@Slf4j
public class FantasticUtil {
    public static final ThreadLocal<SimpleDateFormat> SDF_2_D = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyMMdd"));
    public static final ThreadLocal<SimpleDateFormat> YYYY_MM_DD = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));
    public static final ThreadLocal<SimpleDateFormat> TL_SDF_2_MONTH = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyMM"));
    public static final ThreadLocal<SimpleDateFormat> TL_SDF_2_S = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyMMddHHmmss"));
    public static final ThreadLocal<SimpleDateFormat> TL_SDF_SHANDONG_TCP = ThreadLocal.withInitial(() -> new SimpleDateFormat("MMddHHmmss"));
    public static final ThreadLocal<SimpleDateFormat> SDF_4_NOTIFY = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    public static final ThreadLocal<SimpleDateFormat> TL_SDF_2_MS = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS"));
    public static final ThreadLocal<SimpleDateFormat> TL_SDF_2_MS2 = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyMMddHHmmssSSS"));

    public static final ThreadLocal<DecimalFormat> TL_DF = ThreadLocal.withInitial(() -> new DecimalFormat("0000"));

    public static final ThreadLocal<DecimalFormat> TL_DF_6 = ThreadLocal.withInitial(() -> new DecimalFormat("000000"));

    private static AtomicInteger count = new AtomicInteger(0);
    private static AtomicInteger count4SubmitCommand = new AtomicInteger(0);

}
