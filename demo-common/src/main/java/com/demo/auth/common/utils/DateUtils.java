package com.demo.auth.common.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 *
 * @author 鲁砚琨
 */
public class DateUtils {

	public static final String[] parsePatterns = {
		"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
		"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
		"yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    /**
     * 初始化格式:yyyy-MM-dd HH:mm:ss
     */
    public static final String FORMATTING_DATETIME = "yyyy-MM-dd HH:mm:ss";
    /**
     * 初始化格式:yyyy-MM-dd HH:mm:ss.SS
     */
    public static final String FORMATTING_DATETIME_SECOND = "yyyy-MM-dd HH:mm:ss.SS";
    /**
     * 初始化格式:yyyy-MM-dd
     */
    public static final String FORMATTING_DATE = "yyyy-MM-dd";
    /**
     * 初始化格式:yyyy年M月d日
     */
    public static final String FORMATTING_DATE_C = "yyyy年M月d日";
    /**
     * 初始化格式:yyyyMMddHHmmss
     */
    public static final String FORMATTING_DATE_HMS = "yyyyMMddHHmmss";

    /**
     * 获取年
     */
    public static final int YEAR = Calendar.YEAR;
    /**
     * 获取月
     */
    public static final int MONTH = Calendar.MONTH;
    /**
     * 获取日
     */
    public static final int DAY = Calendar.DAY_OF_MONTH;
    /**
     * 获取小时
     */
    public static final int HOUR = Calendar.HOUR;
    /**
     * 获取分钟
     */
    public static final int MINUTE = Calendar.MINUTE;
    /**
     * 获取秒钟
     */
    public static final int SECOND = Calendar.SECOND;
    /**
     * 获取毫秒
     */
    public static final int MILLISECOND = Calendar.MILLISECOND;

    /**
     * 获取时间初始化实例
     *
     * @param formatting 初始化格式
     * @return
     */
    private static SimpleDateFormat getSimpleDateFormat(String formatting) {
        return new SimpleDateFormat(formatting);
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static Date getDate() {
        return Calendar.getInstance().getTime();
    }


    public static String getDate(String formatting){
        return formatting(getDate(), formatting);
    }
    /**
     * 获取当前年
     *
     * @return
     */
    public static int getYear() {
        return getYear(null);
    }

    /**
     * 获取指定时间的年
     *
     * @return
     */
    public static int getYear(Date date) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        return cal.get(YEAR);
    }

    /**
     * 获取当前月
     *
     * @return
     */
    public static int getMonth() {
        return Calendar.getInstance().get(MONTH) + 1;
    }

    /**
     * 获取指定时间的月
     *
     * @return
     */
    public static int getMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        return cal.get(MONTH) + 1;
    }

    /**
     * 获取当前日
     *
     * @return
     */
    public static int getDay() {
        return getDay(null);
    }

    /**
     * 获取指定时间的日
     *
     * @return
     */
    public static int getDay(Date date) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        return cal.get(DAY);
    }

    /**
     * 获取当前时
     *
     * @return
     */
    public static int getHour() {
        return getHour(null);
    }

    /**
     * 获取指定时间的时
     *
     * @return
     */
    public static int getHour(Date date) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        return cal.get(HOUR);
    }

    /**
     * 获取当前分
     *
     * @return
     */
    public static int getMinute() {
        return getMinute(null);
    }

    /**
     * 获取指定时间的分
     *
     * @return
     */
    public static int getMinute(Date date) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        return cal.get(MINUTE);
    }

    /**
     * 获取当前秒
     *
     * @return
     */
    public static int getSecond() {
        return getSecond(null);
    }

    /**
     * 获取指定时间的秒
     *
     * @return
     */
    public static int getSecond(Date date) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        return cal.get(SECOND);
    }

    public static long getcurrentDateToLong(){
        return getDate().getTime();
    }

    /**
     * 时间类型转字符串类型
     *
     * @param date
     * @return
     */
    public static String formatting(Date date) {
        return formatting(date, FORMATTING_DATETIME);
    }

    /**
     * 时间类型转字符串类型
     *
     * @param date
     * @param formatting
     * @return
     */
    public static String formatting(Date date, String formatting) {
        return getSimpleDateFormat(formatting).format(date);
    }

    /**
     * 字符串类型转时间类型
     *
     * @param date
     * @return
     */
    public static Date formatting(String date, String formatting) {
        try {
            if (isDate(date, formatting)) {
                return getSimpleDateFormat(formatting).parse(date);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 字符串类型转时间类型
     *
     * @param date
     * @return
     */
    public static Date formatting(String date) {
        return formatting(date, FORMATTING_DATETIME);
    }

    /**
     * 获取当前 年月日时分秒毫秒
     *
     * @return
     */
    public static String getCurrentDatetimeSecond() {
        return DateUtils.formatting(DateUtils.getDate(), DateUtils.FORMATTING_DATETIME_SECOND);
    }

    /**
     * 获取当前 年月日时分秒毫秒(无格式)
     *
     * @return
     */
    public static String getCurrentDatetimeSecondNotFormat() {
        return DateUtils.formatting(DateUtils.getDate(), DateUtils.FORMATTING_DATE_HMS);
    }

    /**
     * 判断是否是时间
     *
     * @param dateOrDatetime
     * @return
     */
    public static boolean isDate(String dateOrDatetime, String formatting) {
        try {
            getSimpleDateFormat(formatting).parse(dateOrDatetime);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取某时间的之前或之后的时间
     *
     * @param datetime 时间
     * @param value    计算的值
     *                 正数为指定时间的下一个计算类型
     *                 负数数为指定时间的上一个计算类型
     * @param type     计算类型
     *                 DateUtil.YEAR 获取年
     *                 DateUtil.MONTH 获取月
     *                 DateUtil.DAY 获取天
     *                 DateUtil.HOUR 获取小时
     *                 DateUtil.MINUTE 获取分钟
     *                 DateUtil.SECOND 获取秒钟
     * @return
     */
    public static Date getCalculateDate(Date datetime, int value, int type) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(datetime);
        cal.add(type, value);
        return cal.getTime();
    }

    /**
     * 计算时间差
     *
     * @param first 第一个时间
     * @param last  第二个时间
     * @param type  计算相差结果类型：
     *              DateUtil.DAY 获取相差多少天
     *              DateUtil.HOUR 获取相差多少小时
     *              DateUtil.MINUTE 获取相差多少分钟
     *              DateUtil.SECOND 获取相差多少秒钟
     * @return 返回 -1 则获取失败
     */
    public static long getDateDiff(Date first, Date last, int type) {
        long millisecond = 1; // 1毫秒
        long second = millisecond * 1000; // 一秒钟的毫秒值
        long minute = second * 60; // 一分钟的毫秒值
        long hour = minute * 60; // 一小时的毫秒值
        long day = hour * 24; // 一天的毫秒值
        long diff = Math.abs(first.getTime() - last.getTime());
        if (DAY == type) { // 计算相差多少天
            return diff / day;
        } else if (HOUR == type) { // 计算相差多少小时
            return diff / hour;
        } else if (MINUTE == type) { // 计算相差多少分钟
            return diff / minute;
        } else if (SECOND == type) { // 计算相差多少秒钟
            return diff / second;
        } else if (MILLISECOND == type) { // 计算相差多少毫秒
            return diff / millisecond;
        }
        return -1L;
    }

    /**
     * 获取当前的周期
     *
     * @return
     */
    public static int getCurrentDayOfWeek() {
        Calendar cal = Calendar.getInstance();
        int week = cal.get(Calendar.DAY_OF_WEEK);
        return week == 1 ? 7 : week - 1;
    }

    /**
     * 获取指定时间的周期
     *
     * @param date 当前日期
     * @return
     */
    public static int getCurrentDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int week = cal.get(Calendar.DAY_OF_WEEK);
        return week == 1 ? 7 : week - 1;
    }

    /**
     * 比较时间的大小
     *
     * @param first 第一个时间
     * @param last  最后一个时间
     * @return -1 first<last, 0 first=last, 1 first>last
     */
    public static int  compareTo(Date first, Date last) {
        Calendar calFirst = Calendar.getInstance();
        calFirst.setTime(first);
        Calendar calLast = Calendar.getInstance();
        calLast.setTime(last);
        return calFirst.compareTo(calLast);
    }

    /**
     * 获取当月第一天
     *
     * @return
     */
    public static Date currentMonthFirstDay() {
        return monthFirstDay(null);
    }

    /**
     * 获取指定日期的当月第一天
     *
     * @param date
     * @return
     */
    public static Date monthFirstDay(Date date) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        cal.add(Calendar.MONTH, 0);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    /**
     * 获取当月最后一天
     *
     * @return
     */
    public static Date currentMonthLastDay() {
        return monthLastDay(null);
    }

    /**
     * 获取指定日期的当月最后一天
     *
     * @return
     */
    public static Date monthLastDay(Date date) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    /**
     * 获取当前年是否为闰年
     *
     * @return
     */
    public static boolean isLeapYear() {
        return isLeapYear(getYear());
    }

    /**
     * 获取指定时间是否为闰年
     *
     * @param date
     * @return
     */
    public static boolean isLeapYear(Date date) {
        return isLeapYear(getYear(date));
    }

    /**
     * 获取指定时间是否为闰年
     *
     * @param year
     * @return
     */
    public static boolean isLeapYear(int year) {
        return (year % 4) == 0;
    }


    /**
     * 获取当月实际天数
     * @param date
     * @return
     */
    public static int getActualMaximumDaysOfMonth(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.getActualMaximum(Calendar.DATE);
    }

}
