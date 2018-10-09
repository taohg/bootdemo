package com.thg.bootdemo.exception;

import org.springframework.core.env.Environment;

import javax.annotation.Resource;

public abstract class AbstractExceptionEnv {

    @Resource
    private static Environment _sharedEnv = null;

    public static String getString(String key, String defval) {
        return _sharedEnv.getProperty(key, defval);
    }

    public static boolean isLoaded() {
        return _sharedEnv != null;
    }
}
