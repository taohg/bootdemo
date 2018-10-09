package com.thg.bootdemo.exception;

import com.thg.bootdemo.common.Constants;
import org.springframework.util.StringUtils;

public class GeneralException extends Exception{
    private String errorCode;

    public GeneralException(String errorCode){
        this.errorCode = errorCode;
    }

    public GeneralException(String errorCode, String message){
        super(message);
        this.errorCode = errorCode;
    }

    public GeneralException(String errorCode, String message, Throwable cause){
        super(message, cause);
        this.errorCode = errorCode;
    }

    public String getErrorCode(){
        if(StringUtils.isEmpty(errorCode)){
            return Constants.EXCEPTION.DEFAULT_ERROR_CODE;
        }
        return errorCode;
    }

    @Override
    public String getMessage() {
        String message = super.getMessage();
        if(StringUtils.isEmpty(message)){
            message = AbstractExceptionEnv.getString(getErrorCode(), null);
        }else{
            message = Constants.EXCEPTION.DEFAULT_ERROR_MESSAGE;
        }
        return super.getMessage();
    }
}
