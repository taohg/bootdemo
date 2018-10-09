package com.thg.bootdemo.common;

import org.springframework.util.StringUtils;

import java.util.Map;

public class CommonUtil {

    public static String getString(Map map, String key){
        String resValue = null;
        if(map.isEmpty() || StringUtils.isEmpty(key)) {
            return resValue;
        }

        resValue = String.valueOf(map.get(key));
        return resValue;
    }
}
