package com.example.nc_basic_biz.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/8/3 3:58
 */
public class TimeUtils {

    private static final long ONE_MINUTE = 60000L;
    private static final long ONE_HOUR = 3600000L;
    private static final long ONE_DAY = 86400000L;
    private static final long ONE_WEEK = 604800000L;

    private static final String ONE_SECOND_AGO = "秒前";
    private static final String ONE_MINUTE_AGO = "分钟前";
    private static final String ONE_HOUR_AGO = "小时前";
    private static final String ONE_DAY_AGO = "天前";
    private static final String ONE_MONTH_AGO = "月前";
    private static final String ONE_YEAR_AGO = "年前";

    /**
     * 将秒数转换为分钟小时(最大取小时)
     */
    public static String getTime(long duration) {
        if (duration >= 3600) {
            long d = duration / 3600;
            long t = duration - (long) (d * 3600);
            if (t > 60) {
                long m = t / 60;
                long s = t - (long) (m * 60);
                return d + "h" + " " + m + "'" + " " + s + "''";
            } else {
                return d + "h" + " " + 0 + "'" + " " + t + "''";
            }
        } else if (duration < 3600 && duration >= 60) {
            long d = duration / 60;
            long t = duration - (long) (d * 60);
            if (d < 10) {
                if (t < 10) {
                    return "0" + d + "'" + " " + "0" + t + "''";
                }
                return "0" + d + "'" + " " + t + "''";
            }
            return d + "'" + " " + t + "''";
        } else if (duration < 60) {
            if (duration < 10) {
                return "0" + duration + "''";
            }
            return duration + "''";
        }
        return "";
    }

    /**
     * 把毫秒转化成日期
     *
     * @param dateFormat
     * @param millSec(毫秒数)
     * @return
     */
    public static Date transferLongToDate(String dateFormat, Long millSec) {

        Date parse = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
            Date date = new Date(millSec);
            String format = sdf.format(date);
            parse = sdf.parse(format);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    }

    /**
     * 把时间转换成几天前，几小时前格式
     *
     * @param time
     * @return
     */
    public static String format(long time) {

        Date date = transferLongToDate("yyyy-MM-dd HH:m:s", time);
        long delta = new Date().getTime() - date.getTime();
        if (delta < 1L * ONE_MINUTE) {
            long seconds = toSeconds(delta);
            return (seconds <= 0 ? 1 : seconds) + ONE_SECOND_AGO;
        }

        if (delta < 45L * ONE_MINUTE) {

            long minutes = toMinutes(delta);
            return (minutes <= 0 ? 1 : minutes) + ONE_MINUTE_AGO;
        }

        if (delta < 24L * ONE_HOUR) {

            long hours = toHours(delta);
            return (hours <= 0 ? 1 : hours) + ONE_HOUR_AGO;
        }

        if (delta < 48L * ONE_HOUR) {
            return "昨天";
        }

        if (delta < 30L * ONE_DAY) {
            long days = toDays(delta);
            return (days <= 0 ? 1 : days) + ONE_DAY_AGO;
        }

        if (delta < 12L * 4L * ONE_WEEK) {

            long months = toMonths(delta);
            return (months <= 0 ? 1 : months) + ONE_MONTH_AGO;

        } else {

            long years = toYears(delta);
            return (years <= 0 ? 1 : years) + ONE_YEAR_AGO;

        }
    }

    private static long toSeconds(long date) {

        return date / 1000L;
    }

    private static long toMinutes(long date) {

        return toSeconds(date) / 60L;

    }

    private static long toHours(long date) {

        return toMinutes(date) / 60L;
    }

    private static long toDays(long date) {

        return toHours(date) / 24L;
    }

    private static long toMonths(long date) {

        return toDays(date) / 30L;
    }

    private static long toYears(long date) {

        return toMonths(date) / 365L;
    }

    /**
     * 得到每日日报的刷新时间
     *
     * @param date
     * @return
     */
    public static String getDate(String date) {

        //20170501
        String str_1 = date.substring(0, 4);//2017
        String str_5 = date.substring(5, 6);//5
        String str_6 = date.substring(6, 8);//01
        if (str_6.endsWith("01")) {

            if (str_5.endsWith("01")) {
                return new StringBuffer(((int) Integer.valueOf(str_1)) - 1).append("1231").toString();
            } else {
                String str_46 = date.substring(4, 6);
                if (str_46.endsWith("03")) {
                    return new StringBuilder(str_1).append("0228").toString();
                } else if (str_46.endsWith("05")//1,2,3,5,7,8,10,12  4,6,9,11
                        || str_46.endsWith("07")
                        || str_46.endsWith("10")) {
                    return new StringBuilder(str_1).append("0").append(((int) Integer.valueOf(str_46)) - 1).append("30").toString();
                } else if (str_46.endsWith("12")) {
                    return new StringBuilder(str_1).append(((int) Integer.valueOf(str_46)) - 1).append("30").toString();
                } else {
                    return new StringBuilder(str_1).append(((int) Integer.valueOf(str_46)) - 1).append("31").toString();
                }
            }
        } else {
            String s1 = date.substring(0, 6);//201705
            int i6 = Integer.valueOf(str_6);
            String s2 = String.valueOf(i6 - 1);
            return i6 <= 10 ? new StringBuilder(s1).append("0").append(s2).toString() : new StringBuilder(s1).append(s2).toString();
        }
    }

    /**
     * 时长格式化显示
     *
     * @param time
     * @return
     */
    public static String generateTime(long time) {
        int totalSeconds = (int) (time / 1000);
        int seconds = totalSeconds % 60;
        int minutes = (totalSeconds / 60) % 60;
        int hours = totalSeconds / 3600;
        return hours > 0 ? String.format("%02d:%02d:%02d", hours, minutes, seconds) : String.format("%02d:%02d", minutes, seconds);
    }
}
