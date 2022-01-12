package domain;

import java.util.Date;

public class Item {

    private Long id;
    private String label;
    private Date date;

    public Item() {
    }

    public Item(Long id, String label, Date date) {
        this.id = id;
        this.label = label;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
