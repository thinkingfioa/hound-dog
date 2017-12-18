package org.vlis.dog.util;

import org.joda.time.DateTimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vlis.dog.constant.StartUpRoleEnum;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author thinking_fioa
 * @createTime 2017/12/18
 * @description 时间工具，用于时间的转换或着时间段的切分
 */


public class DateTimeUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(DateTimeUtil.class);
    private static final String DATE_FORMAT="yyyy-MM-dd HH:mm:ss";

//    public static void main(String [] args) {
//        String startTime = "2017-12-13 12:26:56";
//        String endTime = "2017-12-13 12:46:56";
//
//        List<StartEndTimeWrapper> list = splitTime(DATE_FORMAT, startTime, endTime, 6);
//        for(StartEndTimeWrapper one : list) {
//            System.out.println("[startTime]:"+one.getStartTime()+", [endTime]:"+one.getEndTime());
//        }
//    }

    /**
     * 切分时间片。按照论文中的格式切割
     * @param start 开始时间
     * @param end 结束时间
     * @param interval 时间间隔(单位：分钟)
     * @return
     */
    public static List<StartEndTimeWrapper> splitTime(String dateFormat, String start, String end, int interval){
        List<StartEndTimeWrapper> list = new ArrayList<StartEndTimeWrapper>();
        try {
            Date startDate = convertString2Date(dateFormat,start);
            Date endDate = convertString2Date(dateFormat,end);
            while(startDate.getTime()<=endDate.getTime()){
                String startTime = convertDate2String(dateFormat,startDate);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(startDate);
                calendar.add(Calendar.MINUTE,interval);


                if(calendar.getTime().getTime()>endDate.getTime()){
                    if(!startDate.equals(endDate)){
                        String endTime = convertDate2String(dateFormat, endDate);
                        list.add(new StartEndTimeWrapper(startTime, endTime));
                    }
                    // 下次取一半
                    Calendar nextCalendar = Calendar.getInstance();
                    nextCalendar.setTime(startDate);
                    nextCalendar.add(Calendar.MINUTE,interval/2);
                    startDate = nextCalendar.getTime();
                } else {
                    list.add(new StartEndTimeWrapper(startTime, convertDate2String(DATE_FORMAT, calendar.getTime())));
                    // 下次取一半
                    Calendar nextCalendar = Calendar.getInstance();
                    nextCalendar.setTime(startDate);
                    nextCalendar.add(Calendar.MINUTE,interval/2);
                    startDate = nextCalendar.getTime();
                }

            }
        } catch(ParseException ex) {
            LOGGER.error("[start]:{}, [end]:{}, [interval]:{}", start, end, interval, ex);
            System.out.println("[start]:"+ start +",[end]:"+end+",[interval]:"+interval);
        }

        return list;
    }

    /**
     * 将对应的String类型的time转换成对应格式的{@see java.util.Date}
     * @param dateFormatString 格式
     * @param time 给定时间字符串
     * @return 对应的Date
     * @throws ParseException
     */
    public static Date convertString2Date(String dateFormatString, String time) throws ParseException{
        if(null == dateFormatString || null == time || dateFormatString.isEmpty() || time.isEmpty()) {
            throw new NullPointerException("parameters is null.");
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormatString);
        return simpleDateFormat.parse(time);
    }

    /**
     * 将对应的对应格式的{@see java.util.Date}的date转换成String类型的时间格式
     * @param dateFormatString 格式
     * @param date 日期
     * @return 对应的String时间格式
     * @throws ParseException
     */
    public static String convertDate2String(String dateFormatString, Date date) throws ParseException{
        if(null == dateFormatString || null == date || dateFormatString.isEmpty()) {
            throw new NullPointerException("parameters is null.");
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormatString);
        return simpleDateFormat.format(date);
    }


    private DateTimeUtil() {
        throw new IllegalAccessError("can't use constructed.");
    }


    public static class StartEndTimeWrapper {
        private String startTime;
        private String endTime;

        StartEndTimeWrapper(String startTime, String endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }
    }
}
