package com.allianz.maventest.webservice.request;

import java.io.Serializable;
import java.time.LocalDate;

public class UpdateCompanyRequest implements Serializable {

    private static final long serialVersionUID = -4611270335709376815L;
    
    private String label;
    private LocalDate date;

    public UpdateCompanyRequest() {
    }

    public UpdateCompanyRequest(String label, LocalDate date) {
        this.label = label;
        this.date = date;
    }

    public String getLabel() {
        return label;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
