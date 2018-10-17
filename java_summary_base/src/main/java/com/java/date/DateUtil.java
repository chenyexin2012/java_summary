package com.java.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateUtil {

    /**
     * 24小时制
     */
    public final static String pattern1 = "yyyy-MM-dd HH:mm:ss";

    /**
     * 12小时制
     */
    public final static String pattern2 = "yyyy-MM-dd hh:mm:ss";

    /**
     * 格式化时间
     * @param date
     * @param pattern
     * @return
     */
    public static String dateFormat(Date date, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    /**
     * 字符串转Date
     * @param date
     * @param pattern
     * @return
     */
    public static Date parseDate(String date, String pattern) {

        SimpleDateFormat format = new SimpleDateFormat(pattern);
        try {
            return format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取格式化的当前时间字符串
     * @param pattern
     * @return
     */
    public static String getCurrentDateStr(String pattern) {
        return dateFormat(new Date(), pattern);
    }

    /**
     * 获取格式为：yyyy-MM-dd HH:mm:ss 的当前时间字符串
     * @return
     */
    public static String getCurrentDateStr() {
        return getCurrentDateStr(pattern1);
    }


}
