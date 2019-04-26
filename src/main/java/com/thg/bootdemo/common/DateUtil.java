package com.thg.bootdemo.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public interface DATE_PATTERN{
        public static final String YYYYMMDD = "yyyyMMdd";
        public static final String YYYYMMDDHHMISS = "yyyyMMddhhmmss";
        public static final String YYYY_MM_DD_HH_MI_SS = "yyyy-MM-dd hh:mm:ss";

    }

    public static Date getSysdate(){
        Calendar c = Calendar.getInstance();
        return c.getTime();
    }

    public static String date2String(Date date){
        return date2String(date, DATE_PATTERN.YYYY_MM_DD_HH_MI_SS);
    }

    public static String date2String(Date date, String pattern){
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }
}
