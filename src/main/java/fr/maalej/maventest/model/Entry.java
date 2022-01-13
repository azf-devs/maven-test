package fr.maalej.maventest.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.Objects;

public class Entry {
    @JsonProperty(required = true)
    private Integer id;

    @JsonProperty(required = true)
    private String label;

    @JsonProperty(required = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;

    public Entry() {
    }

    public Entry(Integer id, String label, Date date) {
        this.id = id;
        this.label = label;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public Date getDate() {
        return date;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Entry)) {
            return false;
        }

        Entry other = (Entry) obj;

        if (other.getId() != this.id || other.getLabel() != this.label) {
            return false;
        }

        if (this.date == other.getDate()) {
            return true;
        }

        if (this.date == null || other.getDate() == null) {
            return false;
        }

        return this.date.equals(other.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.label);
    }
}
