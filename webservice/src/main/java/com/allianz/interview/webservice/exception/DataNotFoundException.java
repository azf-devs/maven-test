package com.allianz.interview.webservice.exception;

import org.springframework.http.HttpStatus;

public class DataNotFoundException extends InterviewDataBusinessException {

    public DataNotFoundException() {
        super("DATA_NOT_FOUND", "Aucune donnée ne correspond à l'identifiant demandé", HttpStatus.NOT_FOUND.value());
    }


}
