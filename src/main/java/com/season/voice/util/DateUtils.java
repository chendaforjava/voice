package com.season.voice.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class DateUtils {


    private static final Log logger = LogFactory.getLog(DateUtils.class);

    public static final String DATE_SHORT_FORMAT = "yyyyMMdd";
    public static final String DATE_CH_FORMAT = "yyyy年MM月dd日";

    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String T_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss.S";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_MONTH_FORMAT = "yyyy-MM";
    public static final String TIME_FORMAT = "HH:mm:ss";

    public static final String DATE_TIME_FORMAT1 = "yyyy-MM-dd'T'HH:mm";

    //例：5月12日14:30分
    public static final String MINUTE_FORMAT = "yyyy年MM月dd日 HH:mm分";

    public static final String DAYTIME_START = "00:00:00";
    public static final String DAYTIME_END = "23:59:59";

    private static final String[] FORMATS = {"yyyy-MM-dd", "yyyy-MM-dd HH:mm",
            "yyyy-MM-dd HH:mm:ss", "HH:mm", "HH:mm:ss", "yyyy-MM",
            "yyyy-MM-dd HH:mm:ss.S"};

    public static final Map<Integer,String> dateMap = new HashMap<>();

    static {
        dateMap.put(1,"JAN");
        dateMap.put(2,"FEB");
        dateMap.put(3,"MAR");
        dateMap.put(4,"APR");
        dateMap.put(5,"MAY");
        dateMap.put(6,"JUN");
        dateMap.put(7,"JUL");
        dateMap.put(8,"AUG");
        dateMap.put(9,"SEPT");
        dateMap.put(10,"OCT");
        dateMap.put(11,"NOV");
        dateMap.put(12,"DEC");
    }

    public static String getDateEg(Integer date) {
        return dateMap.get(date);
    }


    /**
     * 字符串转换成日期 主要解决json传入日期问题
     */
    public static Date convert(String str) {
        if (str != null && str.length() > 0) {
            if (str.length() > 10 && str.charAt(10) == 'T') {
                str = str.replace('T', ' ');// 去掉json-lib加的T字母
            }
            for (String format : FORMATS) {
                if (format.length() == str.length()) {
                    try {
                        Date date = new SimpleDateFormat(format).parse(str);
                        return date;
                    } catch (ParseException e) {
                        if (logger.isWarnEnabled()) {
                            logger.warn(e.getMessage());
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     * 字符串、日期格式 转换日期
     *
     * @param format 例如: "yyyy-MM-dd HH:mm:ss"
     * @param str    例如: "2012-12-03 23:21:24"
     */
    public static Date convert(String str, String format) {
        if (!StringUtils.isEmpty(str)) {
            try {
                Date date = new SimpleDateFormat(format).parse(str);
                return date;
            } catch (ParseException e) {
                if (logger.isWarnEnabled()) {
                    logger.warn(e.getMessage());
                }
            }
        }
        return null;
    }

    /**
     * 将日期转换成format字符串
     * @param date 例如: Sun Jun 10 09:18:00 CST 2018
     * @param dateFormat 例如: "yyyy-MM-dd HH:mm:ss"
     */
    public static String convert(Date date, String dateFormat) {
        if (date == null) {
            return null;
        }
        if (null == dateFormat) {
            dateFormat = DATE_TIME_FORMAT;
        }
        return new SimpleDateFormat(dateFormat).format(date);
    }

    /**
     * 获取半小时之前的时间
     *
     * @return
     */
    public static String getAhalfhourbeforeDate() {
        long curren = System.currentTimeMillis();
        curren -= 30 * 60 * 1000;
        Date da = new Date(curren);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(da);
    }

    /**
     * 将字符串转换成时间 格式:yyyy-MM-dd HH:mm:ss
     *
     * @param str
     * @return
     */
    public static Date StrToDate(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 将标准时间格式转换成yyyy-MM-dd HH:mm:ss
     *
     * @param d
     * @return
     */
    public static String getDateTime(Date d) {
        String ft = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat format = new SimpleDateFormat(ft);
        return format.format(d);
    }

    /**
     * 将标准时间格式转换成yyyy-MM-dd
     *
     * @param d
     * @return
     */
    public static String getDate(Date d) {
        String ft = "yyyy-MM-dd";
        SimpleDateFormat format = new SimpleDateFormat(ft);
        return format.format(d);
    }

    /**
     * 将标准时间转换为月
     * @param d
     * @return
     */
    public static String getMonthFormat(Date d){
        SimpleDateFormat format = new SimpleDateFormat(DateUtils.DATE_MONTH_FORMAT);
        return format.format(d);
    }

    public static String getDateStr(Date d,String ft) {
        SimpleDateFormat format = new SimpleDateFormat(ft);
        return format.format(d);
    }

    /**
     * 获取当前时间 yyyy-MM-dd格式
     *
     * @return
     */
    public static String currentDate() {
        String ft = "yyyy-MM-dd";
        SimpleDateFormat format = new SimpleDateFormat(ft);
        return format.format(new Date());
    }

    /**
     * 获取当前时间  yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String currentDateTime() {
        String ft = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat format = new SimpleDateFormat(ft);
        return format.format(new Date());
    }


    /**
     * 获取两个日期相差的月数
     *
     * @param date1 较大的日期
     * @param date2 较小的日期
     * @return 如果d1>d2返回 月数差 否则返回-1
     */
    public static int getMonthDiff(String date1, String date2, String pattern) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        c1.setTime(sdf.parse(date1));
        c2.setTime(sdf.parse(date2));
		    /*Calendar c1 = Calendar.getInstance();
		    Calendar c2 = Calendar.getInstance();
		    c1.setTime(d1);
		    c2.setTime(d2);*/
        if (c1.getTimeInMillis() < c2.getTimeInMillis()) return -1;
        int year1 = c1.get(Calendar.YEAR);
        int year2 = c2.get(Calendar.YEAR);
        int month1 = c1.get(Calendar.MONTH);
        int month2 = c2.get(Calendar.MONTH);
        int day1 = c1.get(Calendar.DAY_OF_MONTH);
        int day2 = c2.get(Calendar.DAY_OF_MONTH);
        // 获取年的差值 假设 d1 = 2015-8-16 d2 = 2011-9-30
        int yearInterval = year1 - year2;
        // 如果 d1的 月-日 小于 d2的 月-日 那么 yearInterval-- 这样就得到了相差的年数
        if (month1 < month2 || month1 == month2 && day1 < day2) yearInterval--;
        // 获取月数差值
        int monthInterval = (month1 + 12) - month2;
        if (day1 < day2) monthInterval--;
        monthInterval %= 12;
        return yearInterval * 12 + monthInterval;
    }


    public static int compare_date(String date1, String date2, String pattern) {
        // TODO Auto-generated method stub
        Date d1 = StringToDate(date1, pattern);
        Date d2 = StringToDate(date2, pattern);
        if (d1.getTime() > d2.getTime()) {
            //System.out.println("dt1 在dt2前");
            return 1;
        } else if (d1.getTime() < d2.getTime()) {
            //System.out.println("dt1在dt2后");
            return -1;
        } else {//相等
            return 0;
        }
    }

    public static int compare_dates(String date1, String date2, String pattern) {
        // TODO Auto-generated method stub
        Date d1 = StringToDate(date1, pattern);
        Date d2 = StringToDate(date2, pattern);
        if (d1.getTime() > d2.getTime()) {
            //System.out.println("dt1 在dt2前");
            return 1;
        } else if (d1.getTime() < d2.getTime()) {
            //System.out.println("dt1在dt2后");
            return 2;
        } else {//相等
            return 0;
        }
    }

    public static int compare_date_contrast(String date1, String date2, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LocalDateTime start = LocalDateTime.now();//当前时间
        LocalDateTime startTime = LocalDateTime.parse(date2, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));//入住时间
        Duration duration = Duration.between(start,startTime);
        int result = 0;
        long days = duration.toDays(); //相差的天数
        if (days <= 0) { //为当天
            result = 0;
        } else {
            try {
                Long t1 = sdf.parse(date1).getTime();
                Long t2 = sdf.parse(date2).getTime();
                if (t1 > t2) {
                    //System.out.println("t1 在t2前");
                    result = 1;
                } else if (t1 < t2) {
                    //System.out.println("t1在t2后");
                    result = 2;
                } else {//相等
                    result = 0;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 将字符串转换为指点格式的时间戳
     *
     * @param str
     * @param pattern 格式
     * @return
     */
    public static Date StringToDate(String str, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);//小写的mm表示的是分钟
        Date date = null;
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 月份加减
     *
     * @param currentDateTime 要操作的时间
     * @param month_number    减输入负数
     * @return
     * @throws ParseException
     */
    public static String subMonth(String currentDateTime, int month_number) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date dt = sdf.parse(currentDateTime);
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(dt);

        rightNow.add(Calendar.MONTH, month_number);
        Date dt1 = rightNow.getTime();
        String reStr = sdf.format(dt1);
        return reStr;
    }

    /**
     * 获取当前日期是周几
     *
     * @param currentDate 当前日期 yyyy-MM-dd
     * @retrun 星期几
     */
    public static String getWeekendIdx(String currentDate){
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(currentDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * 获取指定日期是星期几<br>
     *
     * @param date
     * @return 指定日期是星期几
     */
    public static String getWeekOfDate(Date date) {
        String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }


    /**
     * 获取本月第一天
     * @return
     */
    public static Date getMonthFirstDay(Date date){
        date = (date == null ? new Date():date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH,1);
        calendar.add(Calendar.MONTH,0);
        String dateStr = getDate(calendar.getTime()) + " 00:00:00";
        return StrToDate(dateStr);
    }

    /**
     * 获取本月最后一天
     * @return
     */
    public static Date getMonthLastDay(Date date){
        date = (date == null ? new Date():date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        String dateStr = getDate(calendar.getTime()) + " 23:59:59";
        return StrToDate(dateStr);
    }

    /**
     * 获取下个月第一天：
     * @return
     */
    public static Date getNextMonthFirstDay(Date date){
        date = (date == null ? new Date():date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH,1);
        calendar.add(Calendar.MONTH,1);
        String dateStr = getDate(calendar.getTime()) + " 00:00:00";
        return StrToDate(dateStr);
    }

    /**
     * 获取两个时间相差分钟数
     */
    public static long getTimeDiff(Date oldTime,Date newTime)  {
        long NTime =oldTime.getTime();
        long OTime = newTime.getTime();
        long diff=(OTime-NTime)/1000/60;
        return diff;
    }

    public static Long getDaysDiff(String startDatestr,String endDateStr){

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            //开始时间
            Date startDate = null;
            startDate = sdf.parse(startDatestr);
            //结束时间
            Date endDate = sdf.parse(endDateStr);
            //得到相差的天数 betweenDate
            return  Long.valueOf(endDate.getTime() - startDate.getTime())/(60*60*24*1000);
        } catch (ParseException e) {
            e.printStackTrace();
            return 0L;
        }
    }


    /**
     * 获取n小时前的时间
     * @param date
     * @param hour
     * @return
     */
    public static Date getHourFront(Date date,int hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - hour);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return calendar.getTime();
    }

    /**
     * 获取n分钟前的时间
     * @param date
     * @param minute
     * @return
     */
    public static Date getMinuteFront(Date date,int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) - minute);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return calendar.getTime();
    }

    /**
     * 校验时间格式是否正确
     * @param dateStr
     * @param pattern
     * @return
     */
    public static boolean isValid(String dateStr,String pattern) {
        try {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.CHINA);
            dateFormatter.parse(dateStr);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * 计算相差时间
     * @param date1
     * @param date2
     * @return
     */
    public static String getDateDiff(String date1, String date2) {
        long hour = 0L;
        long min = 0L;
        try {
            SimpleDateFormat df = new SimpleDateFormat(DATE_TIME_FORMAT);
            Date now = df.parse(date2);
            Date date = df.parse(date1);
            long l = now.getTime() - date.getTime();
            long day = l / (24 * 60 * 60 * 1000);
            hour = (l / (60 * 60 * 1000) - day * 24);
            min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
        } catch (ParseException e) {
            logger.error(e);
        }
        String result = hour + "小时" + min + "分钟";
        return result;
    }

//    public static void main(String[] args) {
//        try {
//            long i = compare_date("2020-06-04 11:11:10","2020-06-04 11:11:11",DATE_TIME_FORMAT);
//            //System.out.println(i);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * 获取最新时间
     * @param dateList
     * @return
     */
    public static Date getNewDate(List<Date> dateList) {
        return dateList.stream().max(Date::compareTo).get();
    }

    /**
     * 获取最新时间
     * @param dateList
     * @return
     */
    public static Date getMinDate(List<Date> dateList) {
        return dateList.stream().min(Date::compareTo).get();
    }

    /**
     * 获取日期
     *
     * @param dateTime yyyy-MM-dd HH:mm:ss
     * @return yyyy-MM-dd
     */
    public static String getDate(String dateTime) {
        if (org.springframework.util.StringUtils.isEmpty(dateTime)) {
            return null;
        }
        Date date = convert(dateTime);
        return convert(date, DateUtils.DATE_FORMAT);
    }

    /**
     * 获取时间
     *
     * @param dateTime yyyy-MM-dd HH:mm:ss
     * @return HH:mm
     */
    public static String getTime(String dateTime) {
        if (org.springframework.util.StringUtils.isEmpty(dateTime)) {
            return null;
        }
        Date date = convert(dateTime);
        return convert(date, DateUtils.TIME_FORMAT);
    }

    /**
     * 获取明天0点
     */
    public static Date getTodayZeroTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取明天0点
     */
    public static Date getTomorrowZeroTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取昨天0点
     */
    public static Date getYesterdayZeroTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取年份
     *
     * @param date
     * @return
     */
    public static int getYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR);
    }

    /**
     * 获取月份
     *
     * @param date
     * @return
     */
    public static int getMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取日期(多少号)
     *
     * @param date
     * @return
     */
    public static int getDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取当前时间(小时)
     *
     * @param date
     * @return
     */
    public static int getHour(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取当前时间(分)
     *
     * @param date
     * @return
     */
    public static int getMinute(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MINUTE);
    }

    /**
     * 获取当前毫秒
     *
     * @param date
     * @return
     */
    public static long getMillis(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.getTimeInMillis();
    }

    /**
     * 日期增加
     *
     * @param date Date
     * @param day  int
     * @return Date
     */
    public static Date addDate(Date date, int day) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(getMillis(date) + ((long) day) * 24 * 3600 * 1000);
        return c.getTime();
    }

    public static Date addWeek(Date date, int week) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.WEEK_OF_YEAR, week);
        return c.getTime();
    }

    public static Date addSecond(Date date, int second) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.SECOND, second);
        return c.getTime();
    }

    public static Date addMinute(Date date, int minute) {
        if (minute == 0) {
            return date;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MINUTE, minute);
        return c.getTime();
    }

    public static Date addHour(Date date, int hour) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.HOUR, hour);
        return c.getTime();
    }

    public static Date addMonth(Date date, int month) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONDAY, month);
        return c.getTime();
    }

    public static Date addYear(Date date, int year) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR, year);
        return c.getTime();
    }

    /**
     * 获取星期
     *
     * @param date
     * @return
     */
    public static int getWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        dayOfWeek = dayOfWeek - 1;
        if (dayOfWeek == 0) {
            dayOfWeek = 7;
        }
        return dayOfWeek;
    }


    /**
     * 转时区
     * @param s
     * @return
     */
    public static String transferTimeZone(String zoneText) {
        ZoneId zoneId = getZone(zoneText);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.now(), zoneId);
        return localDateTime.toString();
    }

    private static ZoneId getZone(String zoneText) {
        String z = zoneText.replaceAll("\\s*", "").replaceAll("[^A-Za-z]", "");
        String no = zoneText.replaceAll("\\s*", "").replaceAll("[^0-9]", "");
        String zone = z + "+" + no;
        return ZoneId.of(zone);
    }

    public static Date addDay(Date date, int day) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(5, day);
        date = calendar.getTime();
        return date;
    }
}
