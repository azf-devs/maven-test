package com.allianz.maventest.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Company implements Serializable {

    private static final long serialVersionUID = 6316063550290055320L;

    private int id;
    private String label;
    private LocalDate date;

    public Company() {
    }

    public Company(int id, String label, LocalDate date) {
        this.id = id;
        this.label = label;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
