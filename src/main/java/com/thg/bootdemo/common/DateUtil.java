package com.thg.bootdemo.common;

import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public interface DATE_PATTERN{
        public static String YYYYMMDD = "yyyyMMdd";
        public static String YYYYMMDDHHMISS = "yyyyMMddHHmmss";
        public static String YYYY_MM_DD_HH_MI_SS = "yyyy-MM-dd HH:mm:ss";
        public static String YYYY_MM_DD_HH_MI_SS_SS = "yyyy-MM-dd HH:mm:ss S";

    }

    public static Date getSysdate(){
        Calendar c = Calendar.getInstance();
        return c.getTime();
    }

    public static String getSysString(){
        return date2String(getSysdate());
    }

    public static String date2String(Date date){
        return date2String(date, DATE_PATTERN.YYYY_MM_DD_HH_MI_SS);
    }

    public static String date2String(Date date, String pattern){
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    public static Date string2Date(String dateStr, String pattern) throws ParseException {
        return DateUtils.parseDate(dateStr, pattern);
    }

    public static Date add(Date date, int amount, int field){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(field, amount);
        return c.getTime();
    }

    public static int compareDate(Date srcDate, Date destDate){
        return srcDate.compareTo(destDate);
    }

//    public static void main(String[] args) throws ParseException {
//        Date d = new Date();
//        Date d2 = d;
//        Calendar c = Calendar.getInstance();
//        System.out.println("111:" + d);
//        System.out.println("222:" + DateUtil.add(d, -1, Calendar.DATE));
//        System.out.println("333:" + c.getTime());
//        System.out.println("444:" + DateUtil.compareDate(d, d2));
//        System.out.println("555:" + DateUtil.date2String(d, DATE_PATTERN.YYYY_MM_DD_HH_MI_SS_SS));
//        System.out.println("666:" + DateUtil.string2Date("20190426", DATE_PATTERN.YYYYMMDD));
//
//    }
}
