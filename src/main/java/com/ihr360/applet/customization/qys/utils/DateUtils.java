package com.ihr360.applet.customization.qys.utils;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.lang.management.ManagementFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * 时间工具类
 *
 * @author liuqing
 */
@Slf4j
public class DateUtils extends DateUtil{
    public static String YYYY = "yyyy";

    public static String YYYY_MM = "yyyy-MM";

    public static String YYYY_MM_DD = "yyyy-MM-dd";

    public static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    private static final String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    /**
     * 获取当前Date型日期
     *
     * @return Date() 当前日期
     */
    public static Date getNowDate() {
        return new Date();
    }

    /**
     * 获取当前日期, 默认格式为yyyy-MM-dd
     *
     * @return String
     */
    public static String getDate() {
        return dateTimeNow(YYYY_MM_DD);
    }

    public static String getDateYY_MM() {
        return dateTimeNow(YYYY_MM);
    }

    public static String getLastMonthDateYY_MM() {
        return getDateMinusOneMonth(YYYY_MM);
    }

    public static String getTime() {
        return dateTimeNow(YYYY_MM_DD_HH_MM_SS);
    }

    public static String dateTimeNow() {
        return dateTimeNow(YYYYMMDDHHMMSS);
    }

    public static String dateTimeNow(final String format) {
        return parseDateToStr(format, new Date());
    }

    public static String getMinDate(int months) {
        //保留2个多月的数据
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        // 保留2个月的数据
        LocalDateTime sixtyDaysAgo = now.minusMonths(months);
        // 定义最小日期
        LocalDateTime minDate = LocalDateTime.parse("2024-09-01 00:00:00", formatter);
        LocalDateTime resultDate = (sixtyDaysAgo.isBefore(minDate)) ? minDate : sixtyDaysAgo;
        String minDateStr = resultDate.format(formatter);
        return minDateStr.substring(0,7) + "-01 00:00:00";
    }

    public static String dateTimeNormal(Date date) {
        if(date==null) return "";
        return parseDateToStr(YYYY_MM_DD_HH_MM_SS, date);
    }

    public static String dateTime(final Date date) {
        return parseDateToStr(YYYY_MM_DD, date);
    }

    public static String parseDateToStr(final String format, final Date date) {
        return new SimpleDateFormat(format).format(date);
    }

    public static Date dateTime(final String format, final String ts) {
        try {
            return new SimpleDateFormat(format).parse(ts);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 日期路径 即年/月/日 如2018/08/08
     */
    public static String datePath() {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyy/MM/dd");
    }

    /**
     * 日期路径 即年/月/日 如20180808
     */
    public static String dateTime() {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyyMMdd");
    }

    /**
     * 日期型字符串转化为日期 格式
     */
    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        }
        try {
            return parseDate(str.toString());
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取服务器启动时间
     */
    public static Date getServerStartDate() {
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        return new Date(time);
    }

    /**
     * 计算两个时间差
     */
    public static JSONObject getDatePoor(Date endDate, Date nowDate) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        JSONObject object = new JSONObject();
        object.put("day", day);
        object.put("hour", hour);
        object.put("min", min);
        return object;
    }


    /* //时间戳转换日期 */
    public static String stampToTime(String stamp) {
        String sd;
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sd = sdf.format(new Date(Long.parseLong(stamp))); // 时间戳转换日期
        return sd;
    }

    /**
     * 国际时间转换为北京时间
     *
     * @param utc
     * @return
     * @throws ParseException
     */
    public static String utcToCst(String utc) {
        String[] split1 = utc.split("T");
        String split2 = (String) split1[1].subSequence(0, 8);
        return split1[0] + " " + split2;
    }

    /**
     * 获取前一天的日期
     * @return
     */
    public static String getLastDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, -1);
        return simpleDateFormat.format(calendar.getTime());
    }

    public static String getLastNDate(int days){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, days);
        return simpleDateFormat.format(calendar.getTime());
    }

    public static Date convertLongToDate(String timestampString){
        // 将毫秒时间戳转换为 Instant 对象
        if("".equals(timestampString)) return null;
        long timestamp = Long.parseLong(timestampString);
        return new Date(timestamp);
    }

    public static Date convertStringToDate(String dateString){
        if("".equals(dateString)) return null;
        if(null==dateString) return null;
        // 定义日期时间格式
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return formatter.parse(dateString);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date convertStringToDateYYYY_MM(String dateString){
        if("".equals(dateString)) return null;
        if(null==dateString) return null;
        // 定义日期时间格式
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
        try {
            return formatter.parse(dateString);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date convertStringToDateYYYY_MM_DD(String dateString){
        if("".equals(dateString)) return null;
        if(null==dateString) return null;
        // 定义日期时间格式
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return formatter.parse(dateString);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date convertStringToDateTime(String dateString){
        if("".equals(dateString)) return null;
        if(null==dateString) return null;
        // 定义日期时间格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // 解析字符串到 LocalDateTime
        LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter);
        // 将 LocalDateTime 转换为 Instant
        Instant instant = dateTime.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    public static Date convertStringToDateTimeYYYMM(String dateString){
        if("".equals(dateString)) return null;
        if(null==dateString) return null;
        // 定义日期时间格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        // 解析字符串到 LocalDateTime
        LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter);
        // 将 LocalDateTime 转换为 Instant
        Instant instant = dateTime.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    /**
     * 获取当前时间减去一个月的时间
     *
     * @param format 日期格式
     * @return 指定格式的日期字符串
     */
    public static String getDateMinusOneMonth(final String format) {
        LocalDate now = LocalDate.now();
        LocalDate oneMonthAgo = now.minusMonths(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return oneMonthAgo.format(formatter);
    }

    /**
     * 获取从给定日期到现在的月份列表
     *
     * @param startDateStr 起始日期字符串，格式为 yyyy-MM
     * @return 月份列表，格式为 yyyy-MM
     */
    public static List<String> getMonthsFromGivenDateToNow(String startDateStr) {
        List<String> months = new ArrayList<>();
        YearMonth startMonth = YearMonth.parse(startDateStr, DateTimeFormatter.ofPattern(YYYY_MM));
        YearMonth endMonth = YearMonth.now();
        long numberOfMonths = ChronoUnit.MONTHS.between(startMonth, endMonth) + 1;

        for (int i = 0; i < numberOfMonths; i++) {
            months.add(startMonth.plusMonths(i).format(DateTimeFormatter.ofPattern(YYYY_MM)));
        }

        return months;
    }

    public static List<String> getDatesFromGivenDateToNow(String startDateStr) {
        List<String> dates = new ArrayList<>();
        LocalDate startDate = LocalDate.parse(startDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate endDate = LocalDate.now();
        long numberOfDays = ChronoUnit.DAYS.between(startDate, endDate) + 1;

        for (int i = 0; i < numberOfDays; i++) {
            dates.add(startDate.plusDays(i).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }

        return dates;
    }

    public static String getLastDayOfMonthStr(String yearMonth) {
        YearMonth yearMonthObj = YearMonth.parse(yearMonth);
        LocalDate lastDayOfMonth = yearMonthObj.atEndOfMonth();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return lastDayOfMonth.format(formatter).substring(8,10);
    }

    /**
     * 将日期字符串转换为毫秒数
     *
     * @param dateStr 日期字符串，格式为 yyyy-MM-dd HH:mm:ss
     * @return 毫秒数，转换失败返回 null
     */
    public static String parseDateToMillis(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = sdf.parse(dateStr);
            return String.valueOf(date.getTime());
        } catch (ParseException e) {
            log.error("Failed to parse date: {}", dateStr, e);
            return null;
        }
    }



    public static void main(String[] args) {
        System.out.println(DateUtils.getLastMonthDateYY_MM());
        System.out.println(getMonthsFromGivenDateToNow("2025-02"));
        System.out.println(getLastDayOfMonthStr("2025-03"));
        System.out.println(getDatesFromGivenDateToNow("2025-02-25"));
    }

}
