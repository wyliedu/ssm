package com.hszs.stb.common.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日期处理帮助类
 * 
 */
public class DateHelper {
    public static final String YYYY_MM_DD = "yyyy-MM-dd";

	private static final Logger logger = LoggerFactory.getLogger(DateHelper.class);

	private static final String[] DATE_FORMATS = { "yyyyMMdd", YYYY_MM_DD,
		"yyyyMMddHHmm", "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss",
		"yyyy-MM-dd HH:mm" };
	
    /**
     * 一天有多少毫秒
     */
    public static final long MILLI_SECONDS_PER_DAY = 3600 * 24 * 1000;
    
    public static final int SENCONDS_PER_DAY = 3600 * 24;
    
    public static final int SENCONDS_PER_HOUR = 3600;

    /**
     * 把日期格式化为yyyy-MM-dd格式的字符串
     * 
     * @param date
     *            日期对象
     * @return yyyy-MM-dd
     */
    public static String getFormatDate(Date date) {
        try {
            SimpleDateFormat formater = new SimpleDateFormat(YYYY_MM_DD);
            String result = formater.format(date);
            return result;
        } catch (Exception e) {
            return "";
        }
    }
    
    /**
     * 月份1开始，month=1为1月
     * @param year
     * @param month
     * @param date
     * @return
     */
    public static Integer getDateInSecondSince1970(Integer year, Integer month, Integer date){
    	if(year == null || month == null || date == null){
    		return null;
    	}
    	Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, date);
		return (int)(cal.getTimeInMillis()/1000);
    }

    /**
     * 把yyyy-MM-dd格式的字符串转换为另一种格式
     * 
     * @param source
     *            yyyy-MM-dd格式的字符串
     * @param pattern
     *            另外一种格式
     * @return 日期字符串
     */
    public static String getFormatDate(String source, String pattern) {
        SimpleDateFormat formator = new SimpleDateFormat(YYYY_MM_DD);
        try {
            Date date = formator.parse(source);
            SimpleDateFormat targetformator = new SimpleDateFormat(pattern);
            return targetformator.format(date);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 把日期转换成对应格式的字符串
     * 
     * @param source
     *            日期对象
     * @param pattern
     *            日期格式
     * @return 日期字符串
     */
    public static String getFormatDate(Date source, String pattern) {
        try {
            SimpleDateFormat targetformator = new SimpleDateFormat(pattern);
            return targetformator.format(source);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 把日期转换成对应格式的字符串
     * 
     * @param source
     *            日期对象
     * @return 日期字符串
     */
    public static String getFormatTime(Date source) {
        try {
            SimpleDateFormat targetformator = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return targetformator.format(source);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 把日期字符串转换成另外一种格式
     * 
     * @param source
     *            日期字符串
     * @param sourcePatton
     *            日期格式
     * @param pattern
     *            要转换成的日期格式
     * @return 日期字符串
     */
    public static String getFormatDate(String source, String sourcePatton, String pattern) {
        SimpleDateFormat formator = new SimpleDateFormat(sourcePatton);
        try {
            Date date = formator.parse(source);
            SimpleDateFormat targetformator = new SimpleDateFormat(pattern);
            return targetformator.format(date);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 把字符串转换成日期对象
     * 
     * @param source
     *            字符串
     * @param sourcePattern
     *            字符串格式
     * @return 日期对象
     */
    public static Date getDateFromStr(String source, String sourcePattern) {
        SimpleDateFormat formator = new SimpleDateFormat(sourcePattern);
        try {
            Date date = formator.parse(source);
            return date;
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * 把字符串转换成日期对象
     * 
     * @param source
     *            字符串
     * @param sourcePattern
     *            字符串格式
     * @return 日期对象
     */
    public static  java.sql.Date  getSQLDateFromStr(String source, String sourcePattern) {
        SimpleDateFormat formator = new SimpleDateFormat(sourcePattern);
        try {
            java.sql.Date date = new  java.sql.Date(formator.parse(source).getTime());
            return date;
        } catch (Exception e) {
            return null;
        }
    }

	/**
	 * 把yyyy-MM-dd格式的字符串转换成日期
	 * 
	 * @param source
	 *            yyyy-MM-dd格式的字符串
	 * @return Date
	 */
	public static Date getDateFromStr(String source) {
		for (String format : DATE_FORMATS) {
			SimpleDateFormat formator = new SimpleDateFormat(format);
			try {
				Date date = formator.parse(source);
				return date;
			} catch (Exception e) {
			}
		}
		return null;
	}

    /**
     * 获取本地长格式时间（14位）
     * 
     * @return 本地长格式时间
     */
    public static String getLocalLongDate14() {
        String dateString = "";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss", Locale.US);
        dateString = formatter.format(new Date());
        return dateString;
    }

    /**
     * 获取本地长格式时间（6位）时分秒
     * 
     * @return 本地长格式时间
     */
    public static String getLocalLongSecond6() {
        String secondString = "";
        SimpleDateFormat formatter = new SimpleDateFormat("HHmmss", Locale.US);
        secondString = formatter.format(new Date());
        return secondString;
    }

    /**
     * 获取本地长格式时间（9位）时分秒毫秒
     * 
     * @return 本地长格式时间
     */
    public static String getLocalLongMisSecond9() {
        String secondString = "";
        SimpleDateFormat formatter = new SimpleDateFormat("HHmmssSSS", Locale.US);
        secondString = formatter.format(new Date());
        return secondString;
    }

    /**
     * 取得当前时间的字符串
     * 
     * @return yyyy-MM-dd HH:mm:ss格式的字符串
     */
    public static String getNowDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String curTime = sdf.format(new Date());
        return curTime;
    }

    /**
     * 取得月份间隔，忽略天数
     * 
     * @param begin
     *            开始日期
     * @param end
     *            结束日期
     * @return 月份数
     */
    public static int getMonthInterval(Date begin, Date end) {
        // 1、取得两个日期的月份，计算差值d
        // 2、取得两个日期的年份，计算差值m
        // 3、如果d<0,那么m*12+d就是间隔；如果d>0，那么m*12+d就是间隔
        int result = 0;
        Calendar cBegin = Calendar.getInstance();
        cBegin.setTime(begin);
        int bm = cBegin.get(Calendar.MONTH);
        int by = cBegin.get(Calendar.YEAR);

        Calendar cEnd = Calendar.getInstance();
        cEnd.setTime(end);
        int em = cEnd.get(Calendar.MONTH);
        int ey = cEnd.get(Calendar.YEAR);

        result = (ey - by) * 12 + (em - bm);

        return result;
    }

    /**
     * 取得系统当前的时间，以Timestamp 表示
     * 
     * @return 返回Timestamp对象
     */
    public static Timestamp getDateTime() {
        Date date = new Date();
        return (new Timestamp(date.getTime()));
    }

    /**
     * 将指定日期转换为指定格式的字符串
     * 
     * @param ts
     *            ( java.util.Timestamp )
     * @param pattern
     *            日期格式，如："yyyy-MM-dd" ，"yyyy-MM-dd HH:mm:ss"，"HH:mm:ss"
     * @return String
     */
    public static String getDateStringByPattern(Timestamp ts, String pattern) {
        SimpleDateFormat sf = new SimpleDateFormat(pattern);
        String str = sf.format(ts);
        return str;
    }

    /**
     * 取得当前日期
     * 
     * @return Date
     */
    public static Date getCurrentDate() {
        Date date = new Date();
        return date;
    }

    /**
     * 得到本月的第一天
     * 
     * @return
     */
    public static Timestamp getMonthFirstDay(Integer monthoffset) {
        return getMonthFirstDay(monthoffset, null);
    }

    /**
     * 得到某月的第一天
     * 
     * @return
     */
    public static Timestamp getMonthFirstDay(Integer monthoffset, Timestamp time) {
        Calendar calendar = Calendar.getInstance();
        if (time != null) {
            calendar.setTime(time);
        }
        if (monthoffset != null) {
            calendar.add(Calendar.MONTH, monthoffset);
        }
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));

        Date date = getDateFromStr(getFormatDate(calendar.getTime()));
        return new java.sql.Timestamp(date.getTime());
    }

    /**
     * 得到本月的最后一天
     * 
     * @return
     */
    public static Timestamp getMonthLastDay(Integer monthoffset) {
        return getMonthLastDay(monthoffset, null);
    }

    /**
     * 得到本月的最后一天
     * 
     * @return
     */
    public static Timestamp getMonthLastDay(Integer monthoffset, Timestamp time) {
        Calendar calendar = Calendar.getInstance();
        if (time != null) {
            calendar.setTime(time);
        }
        if (monthoffset != null) {
            calendar.add(Calendar.MONTH, monthoffset);
        }
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

        Date date = getDateFromStr(getFormatDate(calendar.getTime()));
        return new java.sql.Timestamp(date.getTime());
    }

    /**
     * 得到理论上不可到达时间 暂定为3000-01-01
     * 
     * @return
     */
    public static Timestamp getMaxTime() {
        String maxtime = "3000-01-01";
        Date date = getDateFromStr(maxtime);
        return new java.sql.Timestamp(date.getTime());
    }

    /**
     * 得到两个日期的间隔毫秒数
     * 
     * @param begin
     *            起始日期
     * @param end
     *            结束日期
     *            <p/>
     *            日期格式
     * @return 天数
     */
    public static long getTimeInterval(Date begin, Date end) {
        return (end.getTime() - begin.getTime());
    }

    /**
     * 增减时间
     * 
     * @param begin
     *            开始时间
     * @param offset
     *            毫秒数
     * @return
     */
    public static Timestamp caculateTime(Timestamp begin, Long offset) {
        long result = begin.getTime() + offset;
        return new Timestamp(result);
    }

    /**
     * dateString must be yyyy-mm-dd hh:mi:ss
     * 
     * @param dateString
     *            日期字符串
     * @return Timestamp
     */
    public static Timestamp getTimestamp(String dateString) {
        try {
            return Timestamp.valueOf(dateString);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 获取随机日期
     * 
     * @param beginTime
     * @param endTime
     * @return
     */
    public static Timestamp randomDate(Timestamp beginTime, Timestamp endTime) {
        try {
            if (beginTime.after(endTime)) {
                return null;
            }
            long time = random(beginTime.getTime(), endTime.getTime());
            return new Timestamp(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static long random(long begin, long end) {
        long rtn = begin + (long) (Math.random() * (end - begin));
        if (rtn == begin || rtn == end) {
            return random(begin, end);
        }
        return rtn;
    }

    /**
     * 随机获取某时间段之间的日期
     * 
     * @param beginTime
     * @param endTime
     * @return
     */
    public static Timestamp randomTime(Timestamp beginTime, Timestamp endTime) {
        try {
            if (beginTime.after(endTime)) {
                return null;
            }
            long time = random(beginTime.getTime(), endTime.getTime());
            Calendar c = Calendar.getInstance();
            c.setTime(new Date(time));
            return new Timestamp(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getYesterday() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return new SimpleDateFormat(YYYY_MM_DD).format(cal.getTime());
    }
   

    /**
     * @param currentDate
     *            指定日期
     * @return 指定日期的前一天
     */
    public static Date getPrevDate(Date currentDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);

        int date = cal.get(Calendar.DATE);
        cal.set(Calendar.DATE, date - 1);

        return cal.getTime();

    }

    /**
     * @return 当前日期的前一天
     */
    public static Date getPrevDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(DateHelper.getCurrentDate());

        int date = cal.get(Calendar.DATE);
        cal.set(Calendar.DATE, date - 1);

        return cal.getTime();

    }

    /**
     * @param currentDate
     *            指定日期
     * @return 指定日期的后一天
     */
    public static Date getNextDate(Date currentDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);

        int date = cal.get(Calendar.DATE);
        cal.set(Calendar.DATE, date + 1);

        return cal.getTime();

    }
    
    /**
     * @return tommorrow moning time in seconds
     */
    public static int getTomorrowMorning(){
    	 Calendar cal = Calendar.getInstance();
    	 cal.setTimeInMillis(System.currentTimeMillis());
    	 
    	 int date = cal.get(Calendar.DATE);
         cal.set(Calendar.DATE, date + 1);
         
         cal.set(Calendar.HOUR_OF_DAY, 0);
         cal.set(Calendar.MINUTE, 0);
         cal.set(Calendar.SECOND, 0);

        return  (int) (cal.getTimeInMillis()/1000);
    }

    /**
     * @param date
     * @return 该天的第一秒时间
     */
    public static Date getBeginOfDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);

        return cal.getTime();
    }

    /**
     * @param date
     * @return 该天的最后一秒时间
     */
    public static Date getEndOfDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);

        return cal.getTime();
    }

    /**
     * @param 日期字符串
     *            yyyy-mm-dd
     * @return 该天的最后一秒时间
     */
    public static Date getEndOfDayFromStr(String dateStr) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(DateHelper.getDateFromStr(dateStr));

        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);

        return cal.getTime();
    }

    /**
     * @param date1
     * @param date2
     * @return date1和date2是否是在同一天
     */
    public static boolean isInSameDay(Date date1, Date date2) {
        String dateStr1 = DateHelper.getFormatDate(date1);
        String dateStr2 = DateHelper.getFormatDate(date2);

        return dateStr1.equals(dateStr2);
    }

    /**
     * @param startDate
     *            开始日期
     * @param endDate
     *            结束日期
     * @return 计算两个日期之间相差的星期数， 如果开始日期在结束日期之后，则返回-1
     */
    public static int getWeekInterval(Date startDate, Date endDate) {

        if (startDate.after(endDate)) {
            return -1;
        }

        long days = (endDate.getTime() - startDate.getTime()) / MILLI_SECONDS_PER_DAY;
        int weeks = (int) (days / 7);
        return weeks;
    }

    /**
     * @param date1
     *            前面的日期
     * @param date2
     *            后面的日期
     * @return 后面的日期是否是前面日期的后一天，精确到秒
     */
    public static boolean isContinousDate(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        cal2.add(Calendar.DATE, -1);

        return (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));

    }

    /**
     * 判断是否为同一天，不同年
     * 
     * @param date1
     * @return
     */
    public static boolean isSameDayWithoutYear(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return false;
        }
        String yearFormat = "yyyy";
        String dayFormat = "MMdd";
        return !StringUtils.equals(getFormatDate(date1, yearFormat), getFormatDate(date2, yearFormat)) && StringUtils.equals(getFormatDate(date1, dayFormat), getFormatDate(date2, dayFormat));
    }
    
    public static boolean isInToday(long timestampInSec){
    	Calendar cal = Calendar.getInstance();
    	cal.setTimeInMillis(timestampInSec * 1000);
    	
    	Calendar now = Calendar.getInstance();
    	now.setTimeInMillis(System.currentTimeMillis());
    	return cal.get(Calendar.DAY_OF_MONTH) == now.get(Calendar.DAY_OF_MONTH)
    			&& cal.get(Calendar.MONTH) == now.get(Calendar.MONTH)
    			&& cal.get(Calendar.YEAR) == now.get(Calendar.YEAR);
    	
    }

    /**
     * @param dateStr
     *            yyyy-mm-dd
     * @return 数字yyyymmmmdd
     */
    public static int getIntDate(String dateStr) {
        String[] strArray = StringUtils.split(dateStr, "-");
        return Integer.parseInt(StringUtils.join(strArray));
    }

    /**
     * 返回如20121026表示日期的数字
     * 
     * @return
     */
    public static int getIntDate(Date date) {
        return NumberUtils.toInt(DateHelper.getFormatDate(date, "yyyyMMdd"));
    }
    
    public static Date getDate(long timeInSeconds){
    	Calendar cal = Calendar.getInstance();
    	cal.setTimeInMillis(timeInSeconds*1000);
    	return cal.getTime();
    }
    
    public static String getDateStringFromUnixDate(long timeInSeconds){
    	Calendar cal = Calendar.getInstance();
    	cal.setTimeInMillis(timeInSeconds*1000);
    	
    	return getFormatDate(cal.getTime(), "yyyy-MM-dd HH:mm:ss");
    	
    }
    
    public static int get3MohthExpireTime(){
    	return (int) (System.currentTimeMillis()/1000+3600*24*90);
    }

    public static void main(String[] args) {
        boolean ret = false;
        System.out.println(ret ? DateHelper.getCurrentDate() : DateHelper.getPrevDate());

    }
    
    public static Date getFirstSecondOfNDaysBefore(int days){
    	Calendar now = Calendar.getInstance();
    	Calendar cal = Calendar.getInstance();
    	cal.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DATE), 0, 0, 0);
    	cal.add(Calendar.DAY_OF_MONTH, -days);
    	return cal.getTime();
    }

    
    public static Date getLastSecondOfNDaysBefore(int days){
    	Calendar now = Calendar.getInstance();
    	Calendar cal = Calendar.getInstance();
    	cal.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DATE), 23, 59, 59);
    	cal.add(Calendar.DAY_OF_MONTH, -days);
    	return cal.getTime();
    }
    
    public static Date getLastSecondOfThisMonth(){
    	Calendar now = Calendar.getInstance();
    	Calendar cal = Calendar.getInstance();
    	cal.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), 1, 23, 59, 59);
    	cal.add(Calendar.MONTH, 1);
    	return cal.getTime();
    }
    
    public static int getDaysBetweenDate(Date end,Date start){
    	return (int) Math.ceil((end.getTime()-start.getTime())/86400000.0);
    }

    public static final int currentTimeSeconds(){
		return (int)(System.currentTimeMillis()/1000);
	}
    
    public static int getDayOfYear() {
    	Calendar now = Calendar.getInstance();
    	return now.get(Calendar.DAY_OF_YEAR);
    }
    
    public static int getIntYear() {
    	 return NumberUtils.toInt(DateHelper.getFormatDate(DateHelper.getCurrentDate(), "yyyy"));
    }
    
    public static String timestamp2DateString(Timestamp time){
    	if(time==null)
    		return "";
    	return new SimpleDateFormat("yyyy-MM-dd").format(new Date(time.getTime()));
    }
    
    public static String timestamp2DatetimeString(Timestamp time){
    	if(time==null)
    		return "";
    	return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(time.getTime()));
    }
    
    /**
     * dateString must be yyyy-mm-dd hh:mi:ss
     * 
     * @param dateString
     *            日期字符串
     * @return Timestamp
     */
    public static Timestamp getTimestampFromDate(String dateString) {
    	if(dateString==null||dateString==""){
    		return null;
    	}
        try {
            return Timestamp.valueOf(dateString+" 00:00:00");
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }
}
