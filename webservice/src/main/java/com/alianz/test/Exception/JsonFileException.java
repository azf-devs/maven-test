package com.alianz.test.Exception;

import com.alianz.test.enums.AlianzTestError;

public class JsonFileException extends AlianzTestException {

    public JsonFileException(AlianzTestError alianzTestError) {
        super(alianzTestError);
    }

    public JsonFileException(AlianzTestError alianzTestError, Throwable cause) {
        super(alianzTestError, cause);
    }
}

