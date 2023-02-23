package com.allianz.interview.webservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InterviewDataBusinessException extends RuntimeException {
    private String code;
    private String message;
    private int httpCode;





}
