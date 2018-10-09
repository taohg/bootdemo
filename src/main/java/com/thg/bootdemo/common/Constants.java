package com.thg.bootdemo.common;

public class Constants {

    /**
     * 定义异常信息
     */
    public interface EXCEPTION{
        String DEFAULT_ERROR_CODE = "-1";
        String DEFAULT_ERROR_MESSAGE = "业务异常未定义！";
    }

    /**
     * 定义 redis 缓存 key 规则
     */
    public interface REDIS_KEY{
        String TABLE_SYSUSER = "TABLE_SYSUSER:";
    }
}
