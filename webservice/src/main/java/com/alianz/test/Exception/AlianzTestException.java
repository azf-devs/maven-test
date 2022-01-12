package com.alianz.test.Exception;

import com.alianz.test.enums.AlianzTestError;

public class AlianzTestException extends Exception {

    private String code;

    public  AlianzTestException(String code, String message){
        super(message);
        this.code = code;
    }

    public AlianzTestException(AlianzTestError alianzTestError) {
        super(alianzTestError.message);
        this.code = alianzTestError.code;
    }

    public AlianzTestException(AlianzTestError alianzTestError, Throwable cause) {
        super(alianzTestError.message, cause);
        this.code = alianzTestError.code;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
