package com.alianz.test.Exception.handler;

import com.alianz.test.Exception.ElementNotFoundException;
import com.alianz.test.Exception.JsonFileException;
import com.alianz.test.enums.AlianzTestError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@ControllerAdvice
public class RestAlianzTestExceptionHandler extends ResponseEntityExceptionHandler {



    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException e, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        String errorMessage = "Request Body Json is not formatted correctly";
        log.error(errorMessage, e);
        return buildResponseEntity(new ApiErrorResponse(HttpStatus.BAD_REQUEST, AlianzTestError.REQUEST_BODY_FORMAT.code,
                AlianzTestError.REQUEST_BODY_FORMAT.message, e));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errorMessageList = new ArrayList<>();
        for(ObjectError error : e.getBindingResult().getAllErrors()) {
            errorMessageList.add(error.getDefaultMessage());
        }

        log.error(e.getMessage(), e);
        return buildResponseEntity(new ApiErrorResponse(HttpStatus.BAD_REQUEST, AlianzTestError.FAILED_VALIDATION.code,
                String.join(", ",errorMessageList), e));
    }

    private ResponseEntity<Object> buildResponseEntity(ApiErrorResponse apiErrorResponse) {
        return new ResponseEntity<>(apiErrorResponse, apiErrorResponse.getHttpStatus());
    }

    @ExceptionHandler(ElementNotFoundException.class)
    protected ResponseEntity<Object> handleElementNotFoundException(
            ElementNotFoundException e) {
        log.error(e.getMessage(), e);
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(HttpStatus.NOT_FOUND,
                e.getCode(),
                e.getMessage());
        return buildResponseEntity(apiErrorResponse);
    }

    @ExceptionHandler(JsonFileException.class)
    protected ResponseEntity<Object> handleJsonFileException(
            JsonFileException e) {
        log.error(e.getMessage(), e);
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                e.getCode(),
                e.getMessage(),
                e);
        return buildResponseEntity(apiErrorResponse);
    }
}
