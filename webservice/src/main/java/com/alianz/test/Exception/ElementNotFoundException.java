package com.alianz.test.Exception;

import com.alianz.test.enums.AlianzTestError;

public class ElementNotFoundException extends AlianzTestException {


    public  ElementNotFoundException(String code, String message){
        super(code, message);
    }

    public ElementNotFoundException(AlianzTestError alianzTestError) {
        super(alianzTestError);
    }

    public ElementNotFoundException(AlianzTestError alianzTestError, Throwable cause) {
        super(alianzTestError, cause);
    }
}
