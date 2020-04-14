package cn.com.mjb.candyrebatecore.utils;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 时间处理类
 *
 * @author buu
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateUtil {

    /**
     * 获取系统前时间.
     *
     * @return 转换成yyyy-MM-dd HH:mm:ss
     */
    public static String getStringDate() {
        return com.xiaoleilu.hutool.date.DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 获取指定时间的转换
     *
     * @return 转换成yyyy-MM-dd HH:mm:ss
     */
    public static String getStringToDate(Date date, String format) {
        return com.xiaoleilu.hutool.date.DateUtil.format(date, format);
    }

    /**
     * 获取指定时间的转换
     *
     * @return 转换成yyyy-MM-dd HH:mm:ss
     */
    public static String getStringToDate(long timestamp, String format) {
        return com.xiaoleilu.hutool.date.DateUtil.format(new Date(timestamp), format);
    }
}
