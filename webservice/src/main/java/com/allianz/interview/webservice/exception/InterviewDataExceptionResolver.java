package com.allianz.interview.webservice.exception;


import com.allianz.interview.dto.ErrorData;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.io.IOException;

@Component
@RestControllerAdvice
public class InterviewDataExceptionResolver {

    @ExceptionHandler(value= InterviewDataBusinessException.class)
    public ResponseEntity<Object> handleInterviewDataException(InterviewDataBusinessException ex, WebRequest request) throws IOException {
        ErrorData errorData = ErrorData.builder().httpStatusCode(ex.getHttpCode()).message(ex.getMessage()).code(ex.getCode()).build();
        return new ResponseEntity<>(errorData, new HttpHeaders(), HttpStatus.resolve(errorData.getHttpStatusCode()));
    }

}
